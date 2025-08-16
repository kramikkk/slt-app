package com.example.signlanguagetranslator.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.composable.GestureRecognizerComposeBuilder
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.signlanguagetranslator.ui.theme.Poppins

@Composable
fun GestureRecognitionScreen(
    context: Context,
    coroutineScope: CoroutineScope,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Top part
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
        ) {
            // Title
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Sign Language Recognition",
                fontFamily = Poppins,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }

        // Camera with GestureRecognizerComposeBuilder
        Box(modifier = Modifier.weight(1f)) {
            GestureRecognizerComposeBuilder(
                context = context,
                scope = coroutineScope,
            ).Build(
                modifier = Modifier.fillMaxWidth(0.9f),
                containerShape = RoundedCornerShape(12.dp)
            )
        }

        // Bottom part
        ExpandableCard(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            content = {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.slt3),
                            contentDescription = "Check Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Gesture Recognized:",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Gesture Name",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary // Customize color as needed
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    shape: Shape,
    elevation: CardElevation,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
    ) {
        content()
    }
}