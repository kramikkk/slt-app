package com.example.signlanguagetranslator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signlanguagetranslator.R
import com.example.signlanguagetranslator.ui.theme.Poppins
import java.util.Locale

@Composable
fun DictionaryScreen(
    imageId: Array<Int>,
    names: Array<String>,
    details: Array<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding()) {

        Text(
            text = "Sign Language Dictionary",
            fontFamily = Poppins,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(text = "Search") },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .padding(start = 8.dp)

                )
            }
        )

// Filtered items based on search text
        val filteredIndexes = remember(searchText.text) {
            val searchTerm = searchText.text.lowercase(Locale.ROOT)
            names.indices.filter { names[it].lowercase(Locale.ROOT).contains(searchTerm) }
        }.sorted() // Sort the filtered indexes alphabetically

// LazyColumn for the items
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(filteredIndexes) { index ->
                ColumnItem(
                    modifier,
                    painter = imageId,
                    title = names,
                    details = details,
                    itemIndex = index,
                    navController = navController
                )
            }
        }

    }
}

@Composable
fun ColumnItem(
    modifier: Modifier,
    painter: Array<Int>,
    title: Array<String>,
    details: Array<String>,
    itemIndex: Int,
    navController: NavController
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(route = "DetailScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = painter[itemIndex]),
                contentDescription = title[itemIndex],
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .size(width = 150.dp, height = 100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title[itemIndex],
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = details[itemIndex],
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DictionaryScreenPreview() {
    val imageIds = arrayOf(
        R.drawable.hello,
        R.drawable.i,
        // Add more image resource IDs as needed
    )
    val names = arrayOf(
        "Name 1",
        "Name 2",
        // Add more names as needed
    )
    val ingredients = arrayOf(
        "Ingredient 1",
        "Ingredient 2",
        // Add more ingredients as needed
    )

    // Create a mock NavController for the preview
    val navController = rememberNavController()

    // Call the DictionaryScreen composable
    DictionaryScreen(
        imageId = imageIds,
        names = names,
        details = ingredients,
        navController = navController
    )
}