package com.example.signlanguagetranslator.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Smiley(
    smileyData: SmileyData,
    isSelected: Boolean,
    index: Int,
    count: Int,
    modifier: Modifier = Modifier,
    animationDurationInMillis: Int = 300,
    onClick: () -> Unit,
    fontSize: Int = 10,  // Add a parameter for font size with a default value
    selectedFontSize: Int = 12  // Add a parameter for selected font size with a default value
) {
    val padding: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            0.dp
        } else {
            4.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val size: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            42.dp
        } else {
            34.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val saturation: Float by animateFloatAsState(
        targetValue = if (isSelected) {
            1F
        } else {
            0F
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val matrix = ColorMatrix().apply {
        setToSaturation(saturation)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(smileyData.url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    top = padding,
                )
                .size(size)
                .clip(CircleShape)
                .clickable {
                    onClick()
                },
            colorFilter = ColorFilter.colorMatrix(matrix)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = smileyData.label,
            color = if (isSelected) {
                if (index < (count / 2)) {
                    Color.Red
                } else if (index > (count / 2)) {
                    Color.Green
                } else {
                    Color.Black
                }
            } else {
                Color.DarkGray
            },
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            fontSize = if (isSelected) selectedFontSize.sp else fontSize.sp
        )
    }
}