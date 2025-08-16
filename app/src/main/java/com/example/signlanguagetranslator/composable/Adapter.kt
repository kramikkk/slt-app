//package com.example.signlanguagetranslator.composable
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
//
//
//@Composable
//fun GestureResultItem(result: GestureRecognizerResult) {
//    result.gestures().forEachIndexed { handIndex, gestureList ->
//        gestureList.forEachIndexed { gestureIndex, category ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Column(
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text(text = "Hand $handIndex, Gesture ${gestureIndex + 1}")
//                    Text(text = "Category: ${category.categoryName()}, Score: ${category.score()}")
//                }
//            }
//        }
//    }
//}