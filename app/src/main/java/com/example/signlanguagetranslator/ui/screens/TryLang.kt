package com.example.signlanguagetranslator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.mediapipe.tasks.components.containers.Category
import kotlinx.coroutines.CoroutineScope

@Composable
fun GestureRecognition(
    context: Context,
    coroutineScope: CoroutineScope,
//    categories: List<Category?> // Pass the list of categories here
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sing Language Recognition", style = MaterialTheme.typography.titleLarge)

        GestureRecognizerComposeBuilder(
            context = context,
            scope = coroutineScope,
        ).Build(
            modifier = Modifier.fillMaxWidth(0.9f),
            containerShape = RoundedCornerShape(12.dp)
        )
    }
}


//@Composable
//fun GestureRecognizerResults(
//    categories: List<Category?>,
//) {
//    LazyColumn {
//        items(categories) { category ->
//            // Log the category name and score
//            Log.d("GestureRecognizerResults", "Category name: ${category?.categoryName()}, Score: ${category?.score()}")
//
//            GestureRecognizerResultItem(category?.categoryName(), category?.score())
//        }
//    }
//}
//
//@Composable
//fun GestureRecognizerResultItem(label: String?, score: Float?) {
//    // Your composable function to display the category name and score
//    // This can be similar to GestureRecognizerResultItem in GestureRecognizerResultsAdapter
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text(
//            text = "Label: ${label ?: "--"}",
//            style = MaterialTheme.typography.bodyLarge
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = "Score: ${String.format("%.2f", score ?: 0.0f)}",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}