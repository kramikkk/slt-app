package com.example.signlanguagetranslator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmileyRatingBar(
    data: List<SmileyData>,
    rating: Int,
    setRating: (rating: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    start = 44.dp,
                    end = 44.dp,
                ),
            thickness = 4.dp,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = modifier
                .height(80.dp)
                .fillMaxWidth(),
        ) {
            data.mapIndexed { index, smileyData ->
                Smiley(
                    smileyData = smileyData,
                    isSelected = index == rating,
                    index = index,
                    count = data.size,
                    modifier = Modifier.weight(1F),
                    onClick = {
                        setRating(index)
                    },
                )
            }
        }
    }
}