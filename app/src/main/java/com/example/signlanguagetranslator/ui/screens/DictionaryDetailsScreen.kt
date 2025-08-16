package com.example.signlanguagetranslator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.ui.theme.Poppins

@Composable
fun DictionaryDetailScreen(
    modifier: Modifier = Modifier,
    photos: Array<Int>,
    names: Array<String>,
    details: Array<String>,
    additionalDetails: Array<String>, // Additional details
    itemIndex: Int?
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        Box(modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = photos[itemIndex!!]),
                contentScale = ContentScale.Crop,
                contentDescription = names[itemIndex],
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(300.dp)

            )
        }
        Text(text = names[itemIndex!!], fontSize = 24.sp, fontFamily = Poppins, fontWeight = FontWeight.Bold)
        Text(text = details[itemIndex], fontSize = 18.sp, fontFamily = Poppins)
        Text(text = additionalDetails[itemIndex], fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DictionaryDetailScreenPreview() {
    val samplePhotos = arrayOf(R.drawable.hello)
    val sampleNames = arrayOf("Sample Name")
    val sampleIngredients = arrayOf("Sample Ingredients")
    val sampleItemIndex = 0 // Index of the item to preview

    DictionaryDetailScreen(
        photos = samplePhotos,
        names = sampleNames,
        details = sampleIngredients,
        additionalDetails = sampleIngredients,
        itemIndex = sampleItemIndex
    )
}
