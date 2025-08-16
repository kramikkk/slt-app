package com.example.signlanguagetranslator

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.signlanguagetranslator.composable.DictionaryData
import com.example.signlanguagetranslator.composable.GestureRecognizerHelper
import com.example.signlanguagetranslator.ui.screens.AvatarModelsScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.AvatarModelsSubScreen
import com.example.signlanguagetranslator.ui.screens.DictionaryDetailScreen
import com.example.signlanguagetranslator.ui.screens.DictionaryScreen
import com.example.signlanguagetranslator.ui.screens.GestureRecognitionScreen
import com.example.signlanguagetranslator.ui.screens.HomeScreen
import com.example.signlanguagetranslator.ui.screens.Model3dScreen
import com.example.signlanguagetranslator.ui.screens.OnboardingScreen
import com.example.signlanguagetranslator.ui.screens.PhotoItem
import com.example.signlanguagetranslator.ui.screens.SettingsScreen
import com.example.signlanguagetranslator.ui.screens.homesubscreens.FeatureAvatarScreen
import com.example.signlanguagetranslator.ui.screens.homesubscreens.FeatureDictionaryScreen
import com.example.signlanguagetranslator.ui.screens.homesubscreens.FeatureMoreScreen
import com.example.signlanguagetranslator.ui.screens.homesubscreens.FeatureRecognitionScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.AboutUsScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.ContactScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.CustomizationScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.FeedbackScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.PrivacyPolicyScreen
import com.example.signlanguagetranslator.ui.screens.settingssubscreens.ProfileScreen
//import com.example.signlanguagetranslator.ui.screens.SignToTextScreen
import com.example.signlanguagetranslator.ui.theme.SignLanguageTranslatorTheme
import com.google.android.filament.utils.Utils
import com.google.mediapipe.tasks.components.containers.Category
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {

    companion object {
        init {
            Utils.init()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        // Splash Screen Delay
        var keepSplashOnScreen = true
        val delay = 1000L
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {

            val systemDarkTheme = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(systemDarkTheme) }

            val sharedPreferences = getSharedPreferences("themePrefs", Context.MODE_PRIVATE)
            LaunchedEffect(systemDarkTheme) {
                val savedDarkTheme = sharedPreferences.getBoolean("darkTheme", systemDarkTheme)
                darkTheme = savedDarkTheme
            }

            SignLanguageTranslatorTheme (darkTheme = darkTheme)  {
                val navController = rememberNavController()
                val onBoardingPreferences = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
                val isOnboardingFinished = onBoardingPreferences.getBoolean("isFinished", false)

                LaunchedEffect(key1 = isOnboardingFinished) {
                    if (isOnboardingFinished) {
                        navController.navigate("Home") {
                            popUpTo("Onboarding") { inclusive = true }
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (isOnboardingFinished) "Home" else "Onboarding",
                    ) {
                        composable("Onboarding") {
                            OnboardingScreen(
                                navController = navController,
                                context = this@MainActivity
                            )
                        }
                        composable("Home") {
                            HomeScreen(navController = navController)
                        }
                        composable("Settings") {
                            SettingsScreen(navController = navController)
                        }
                        composable("SignToText") {
                            GestureRecognitionScreen(context = applicationContext, coroutineScope = coroutineScope)
                        }
                        composable("TextToSign") {
                            Model3dScreen()
                        }
                        composable(route = "Dictionary") {
                            DictionaryScreen(   imageId = DictionaryData.entries.map { it.imageId }.toTypedArray(),
                                names = DictionaryData.entries.map { it.name }.toTypedArray(),
                                details = DictionaryData.entries.map { it.detail }.toTypedArray(),navController)
                        }

                        // Dictionary Detail Screen
                        composable(route = "DetailScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            DictionaryDetailScreen(
                                photos = DictionaryData.entries.map { it.imageId }.toTypedArray(),
                                names = DictionaryData.entries.map { it.name }.toTypedArray(),
                                details = DictionaryData.entries.map { it.detail }.toTypedArray(),
                                additionalDetails = DictionaryData.entries.map { it.additionalDetail}. toTypedArray(),
                                itemIndex = backStackEntry.arguments?.getInt("index")
                            )
                        }

                        // SettingsScreen Sub Screens
                        composable("Contact") {
                            ContactScreen(navController)
                        }
                        composable("Feedback") {
                            FeedbackScreen(navController)
                        }
                        composable("PrivacyPolicy") {
                            PrivacyPolicyScreen(navController)
                        }
                        composable("About") {
                            AboutUsScreen(navController)
                        }
                        composable("Customization") {
                            CustomizationScreen(navController, darkTheme = darkTheme) { isDark ->
                                darkTheme = isDark
                                with(sharedPreferences.edit()) {
                                    putBoolean("darkTheme", isDark)
                                    apply()
                                }
                            }
                        }
                        composable("Profile") {
                            ProfileScreen(navController)
                        }
                        composable("AvatarModels") {
                            val samplePhotos = listOf(
                                PhotoItem(1, "Kramik", R.drawable.ava_mark_far),
                                PhotoItem(2, "Mimine", R.drawable.ava_jasmine_far),
                                PhotoItem(3, "Bachira", R.drawable.ava_zyra_far),
                                PhotoItem(4, "Thunder", R.drawable.ava_raymon_far),
                                PhotoItem(5, "Ken", R.drawable.ava_raymon_far),
                                PhotoItem(6, "Jennel", R.drawable.ava_raymon_far),
                            )
                            AvatarModelsScreen(navController, samplePhotos)
                        }
                        composable(
                            "AvatarPreview/{imageRes}",
                            arguments = listOf(navArgument("imageRes") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.mark
                            val name = when (imageRes) {
                                R.drawable.ava_mark_far -> "Kramik"
                                R.drawable.ava_jasmine_far -> "Mimine"
                                R.drawable.ava_zyra_far -> "Bachira"
                                R.drawable.ava_raymon_far -> "Thunder" // Update with appropriate names
                                else -> "Unknown"
                            }
                            AvatarModelsSubScreen(navController, imageRes, name)
                        }

                        //HomeScreen Sub Screens
                        composable("Onboarding") {
                            OnboardingScreen( navController = navController, context = this@MainActivity)
                        }
                        composable("Recognition") {
                            FeatureRecognitionScreen(navController)
                        }
                        composable("Avatar") {
                            FeatureAvatarScreen(navController)
                        }
                        composable("DictionaryFeature") {
                            FeatureDictionaryScreen(navController)
                        }
                        composable("More") {
                            FeatureMoreScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
