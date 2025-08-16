package com.example.signlanguagetranslator.composable

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.google.mediapipe.tasks.vision.core.RunningMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope

class GestureRecognizerComposeBuilder(
    private val context: Context,
    private val scope: CoroutineScope,
) : GestureRecognizerHelper.GestureRecognizerListener {
    private val TAG = "GestureRecognizerBuilder"
    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper
    private var preview: Preview? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var camera: androidx.camera.core.Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private lateinit var previewView: PreviewView
    private lateinit var overlayView: OverlayView
    private lateinit var containerModifier: Modifier
    private var containerShape = RectangleShape

    @Composable
    fun Build(
        modifier: Modifier = Modifier,
        containerShape: Shape = RectangleShape,
    ) {
        this.containerModifier = modifier
        this.containerShape = containerShape
        CheckCameraPermission()
    }

    @Composable
    private fun CheckCameraPermission() {
        var isGranted by remember { mutableStateOf(false) }

        // Initialize the launcher for requesting camera permission
        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isPermissionGranted: Boolean ->
            isGranted = isPermissionGranted
        }

        // Check camera permission and request if necessary
        LaunchedEffect(Unit) {
            isGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

            if (!isGranted) {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        Box(modifier = containerModifier, contentAlignment = Alignment.Center) {
            Surface(shape = containerShape) {
                // If permission is granted, show the camera preview
                if (isGranted) {
                    SingLanguageGestureDetector()
                } else {
                    Text("Camera permission required")
                }
            }
        }
    }

    @Composable
    private fun SingLanguageGestureDetector() {
        val lensFacing = CameraSelector.LENS_FACING_FRONT
        val lifecycleOwner = LocalLifecycleOwner.current
        val context = LocalContext.current
        val preview = Preview.Builder().build()
        val previewView = remember { PreviewView(context) }
        val overlayView = remember { OverlayView(context, null) }
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        DisposableEffect(lifecycleOwner) {
            val lifecycleObserver = LifecycleEventObserver { owner, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> owner.lifecycleScope.launch { doOnResume() }
                    Lifecycle.Event.ON_PAUSE -> owner.lifecycleScope.launch { doOnPause() }
                    else -> {}
                }
            }
            lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            }
        }

        LaunchedEffect(lensFacing) {
            val cameraProvider = context.getCameraProvider()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
            preview.setSurfaceProvider(previewView.surfaceProvider)

            integrateGestureRecognizer(lifecycleOwner)

            withContext(Dispatchers.Default) {
                if (gestureRecognizerHelper.isClosed()) {
                    gestureRecognizerHelper.setupGestureRecognizer()
                }
            }
        }

        this.previewView = previewView.apply { scaleType = PreviewView.ScaleType.FIT_START }
        this.overlayView = overlayView



        Box(modifier = Modifier.aspectRatio(0.75f), contentAlignment = Alignment.Center) {
            AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
            AndroidView(factory = { overlayView }, modifier = Modifier.fillMaxSize())
        }
    }

    private suspend fun doOnResume() {
        if (!::gestureRecognizerHelper.isInitialized) return
        withContext(Dispatchers.Default) {
            if (gestureRecognizerHelper.isClosed()) {
                gestureRecognizerHelper.setupGestureRecognizer()
            }
        }
    }

    private suspend fun doOnPause() {
        if (this::gestureRecognizerHelper.isInitialized) {
            // Close the gesture recognizer and release resources
            withContext(Dispatchers.Default) {
                gestureRecognizerHelper.clearGestureRecognizer()
            }
        }
    }

    private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
        suspendCoroutine { continuation ->
            ProcessCameraProvider.getInstance(this).also { cameraProvider ->
                cameraProvider.addListener({
                    continuation.resume(cameraProvider.get())
                }, ContextCompat.getMainExecutor(this))
            }
        }

    private suspend fun integrateGestureRecognizer(lifecycleOwner: LifecycleOwner) {
        withContext(Dispatchers.Default) {
            gestureRecognizerHelper = GestureRecognizerHelper(
                context = context,
                currentDelegate = GestureRecognizerHelper.DELEGATE_GPU,
                gestureRecognizerListener = this@GestureRecognizerComposeBuilder,
                runningMode = RunningMode.LIVE_STREAM
            )

            previewView.post {
                setUpCamera(lifecycleOwner)
            }
        }
    }

    private fun setUpCamera(lifecycleOwner: LifecycleOwner) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindCameraUseCases(lifecycleOwner)
        }, ContextCompat.getMainExecutor(context))
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun bindCameraUseCases(lifecycleOwner: LifecycleOwner) {
        val cameraProvider = cameraProvider ?: throw IllegalStateException("Camera initialization failed.")
        val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build()

        preview = Preview.Builder()
            .setTargetRotation(previewView.display.rotation)
            .build()

        imageAnalyzer = ImageAnalysis.Builder()
            .setTargetRotation(previewView.display.rotation)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
            .also {
                it.setAnalyzer(Executors.newSingleThreadExecutor(), gestureRecognizerHelper::recognizeLiveStream)
            }

        cameraProvider.unbindAll()

        try {
            camera = cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalyzer
            )
            preview?.setSurfaceProvider(previewView.surfaceProvider)
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }

    override fun onError(error: String, errorCode: Int) {
        scope.launch(Dispatchers.Main) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
        scope.launch(Dispatchers.Main) {
            if (::overlayView.isInitialized) {
                // Pass necessary information to OverlayView for drawing on the canvas
                val detectionResult = resultBundle.results[0]
                overlayView.setResults(
                    detectionResult,
                    resultBundle.inputImageHeight,
                    resultBundle.inputImageWidth
                )

                // Force a redraw
                overlayView.invalidate()
            }
        }
    }
}
