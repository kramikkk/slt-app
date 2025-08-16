package com.example.signlanguagetranslator.ui.screens.settingssubscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.signlanguagetranslator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarModelsSubScreen(navController: NavController, imageRes: Int, name: String) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Model Changed") },
            text = { Text("The model has been changed successfully.") }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
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
        }


        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge, // Increase the text size
            modifier = Modifier
                .padding(vertical = 16.dp) // Increase vertical padding
                .fillMaxWidth(), // Center the text horizontally
            textAlign = TextAlign.Center // Center the text horizontally
        )


        // Box containing the Image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .wrapContentSize(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Full Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Use model")
        }
    }
}
