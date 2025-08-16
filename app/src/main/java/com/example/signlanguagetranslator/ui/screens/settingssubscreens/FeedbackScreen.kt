package com.example.signlanguagetranslator.ui.screens.settingssubscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.composable.SmileyData
import com.example.signlanguagetranslator.composable.SmileyRatingBar
import com.example.signlanguagetranslator.ui.theme.Shapes

@Composable
fun FeedbackScreen(navController: NavController) {
    var feedbackText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

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
            Text(
                text = "Feedback",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = "We value your feedback and would love to hear your thoughts. Your input helps us improve and provide a better service to our users. Feel free to share any suggestions, comments, or concerns you may have. Thank you for taking the time to help us enhance your experience!",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = Shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Give Feedback",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "How is your experience in the app so far?",
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Replace the Row of emoji buttons with SmileyRatingBarSample
                SmileyRatingBarSample()

                Text(
                    text = "Please provide additional comments or suggestions:",
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Add your text field here
                // For example:
                FeedbackTextField(
                    feedbackText = feedbackText,
                    onTextChanged = { feedbackText = it }
                )

                // Add your submit button here
                // For example:
                SubmitButton(
                    feedbackText = feedbackText,
                    onSubmit = {
                        if (feedbackText.isEmpty()) {
                            isError = true
                        } else {
                            isError = false
                        }
                        showDialog = true
                    }
                )
            }
        }
    }

    if (showDialog) {
        if (isError) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Error") },
                text = { Text(text = "Feedback cannot be empty. Please provide your feedback.") },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        } else {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Feedback Submitted") },
                text = { Text(text = "Thank you for your feedback!") },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}

@Composable
fun FeedbackTextField(feedbackText: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = feedbackText,
        onValueChange = onTextChanged,
        label = { Text("Enter your feedback") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_feedback),
                contentDescription = "Feedback",
                modifier = Modifier.size(24.dp),
            )
        }
    )
}

@Composable
fun SubmitButton(feedbackText: String, onSubmit: () -> Unit) {
    Button(
        onClick = onSubmit,
        modifier = Modifier
            .width(200.dp)
            .padding(vertical = 16.dp),
    ) {
        Text(text = "Submit Feedback")
    }
}

@Composable
fun SmileyRatingBarSample() {
    val data: List<SmileyData> = listOf(
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742905.png", "Terrible"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742761.png", "Bad"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742774.png", "Okay"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742940.png", "Good"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742869.png", "Awesome"),
    )

    val (rating, setRating) = remember {
        mutableStateOf(data.size / 2)
    }
    SmileyRatingBar(
        data = data,
        rating = rating,
        setRating = setRating,
    )
}
