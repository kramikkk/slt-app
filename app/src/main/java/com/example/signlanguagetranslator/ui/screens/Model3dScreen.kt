package com.example.signlanguagetranslator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.sceneview.Scene
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.model.getAnimationIndex
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNode

@Composable
fun Model3dScreen() {
    val context = LocalContext.current
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val environmentLoader = rememberEnvironmentLoader(engine)
    val cameraNode = SceneView.DefaultCameraNode(engine)
    val centerNode = rememberNode(engine)
        .addChildNode(cameraNode)
    val customNode = rememberNode {
        ModelNode(
            modelInstance = modelLoader.createModelInstance(
                assetFileLocation = "models/female.glb"
            ),
            scaleToUnits = 1.0f,
            autoAnimate = false
        )
    }
    var userInput by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var animationNotFoundMessage by remember { mutableStateOf("") }





    fun handleSendAnimation(context: Context, customNode: ModelNode) {
        val trimmedInput = userInput.trim()
        val animationIndex = customNode.animator.getAnimationIndex(trimmedInput)

        if (animationIndex != null) {
            customNode.playAnimation(animationIndex, loop = false)
        } else {
            // Animation not found, display a popup message
            showDialog = true
            animationNotFoundMessage = "Animation '$trimmedInput' does not exist." +
                    "\n" +
                    "\nPlease check your spelling and try again." +
                    "\n" +
                    "\nFor phrases like 'I Love You', use 'iloveyou' instead."
        }
    }

    val centerNodePosition = Position(x = 0.0f, y = 0.65f, z = 0.0f)

    Scene(
        modifier = Modifier.fillMaxSize(),
        engine = engine,
        modelLoader = modelLoader,
        cameraNode = cameraNode,
        childNodes = listOf(centerNode, customNode),
        environment = environmentLoader.createHDREnvironment(
            assetFileLocation = "environments/sky2k.hdr"
        )!!,
        onFrame = {
            cameraNode.lookAt(centerNode)
            centerNode.position = centerNodePosition
        },

    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle sending the user input
                    handleSendAnimation(context, customNode)
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        // Handle sending the user input
                        handleSendAnimation(context, customNode)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play"
                    )
                }
            },
            singleLine = true,
            label = { Text(text = "Type here") },
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Animation Not Found")
            },
            text = {
                Text(text = animationNotFoundMessage)
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
}
