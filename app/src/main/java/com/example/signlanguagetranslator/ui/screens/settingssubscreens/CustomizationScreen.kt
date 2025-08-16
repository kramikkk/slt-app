package com.example.signlanguagetranslator.ui.screens.settingssubscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.DrawableCompat.applyTheme
import androidx.navigation.NavController
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.composable.DarkModeSwitch

@Composable
fun CustomizationScreen(navController: NavController, darkTheme: Boolean, onThemeChanged: (Boolean) -> Unit) {
    var languageMenuExpanded by remember { mutableStateOf(false) }
    var signLanguageMenuExpanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(languages[0]) }
    var selectedSignLanguage by remember { mutableStateOf(signLanguages[0]) }
    var showDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                )
                Text(
                    text = "Customization",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dark Mode / Light Mode Switch Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dark Mode / Light Mode Switch",
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    DarkModeSwitch(
                        checked = darkTheme,
                        modifier = Modifier.padding(16.dp),
                        onCheckedChanged = {isChecked ->
                            onThemeChanged(isChecked)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Select Language Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Select Language",
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Button(
                            onClick = { languageMenuExpanded = true },
                        ) {
                            Text(text = selectedLanguage)
                        }

                        DropdownMenu(
                            expanded = languageMenuExpanded,
                            onDismissRequest = { languageMenuExpanded = false }
                        ) {
                            languages.forEach { language ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedLanguage = language
                                        languageMenuExpanded = false
                                    },
                                    text = { Text(language) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Select Sign Language Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Select Sign Language",
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Button(
                            onClick = { signLanguageMenuExpanded = true },
                        ) {
                            Text(text = selectedSignLanguage)
                        }

                        DropdownMenu(
                            expanded = signLanguageMenuExpanded,
                            onDismissRequest = { signLanguageMenuExpanded = false }
                        ) {
                            signLanguages.forEach { language ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedSignLanguage = language
                                        signLanguageMenuExpanded = false
                                    },
                                    text = { Text(language) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Save")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(text = "Settings Changed")
                    },
                    text = {
                        Text(text = "Your settings have been saved.")
                    },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false },
                        ) {
                            Text(text = "OK")
                            }
                    }
                )
            }
        }
    }
}

// Example list of languages
val languages = listOf(
    "English",
    "Spanish",
    "French",
    "German",
    "Chinese"
    // Add more languages as needed
)

// Example list of sign languages
val signLanguages = listOf(
    "American Sign Language (ASL)",
    "British Sign Language (BLSL)",
    "French Sign Language (FLSL)",
    "German Sign Language (GLSL)",
    "Philippine Sign Language",
    // Add more sign languages as needed
)
