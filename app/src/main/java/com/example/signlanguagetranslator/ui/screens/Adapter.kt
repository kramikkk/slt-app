package com.example.signlanguagetranslator.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.mediapipe.tasks.components.containers.Category
import java.util.Locale

class adapter() {

    private var adapterCategories: List<Category> by mutableStateOf(emptyList())
    private var adapterSize: Int by mutableStateOf(0)

    @SuppressLint("NotifyDataSetChanged")
    fun updateResults(categories: List<Category?>) {
        adapterCategories = categories.orEmpty().sortedByDescending { it?.score() } as List<Category>
    }

    fun updateAdapterSize(size: Int) {
        adapterSize = size
    }

    @Composable
    fun GestureRecognizerResultsList() {
        LazyColumn {
            items(adapterCategories) { category ->
                GestureRecognizerResultItem(category = category)
            }
        }
    }

    @Composable
    fun GestureRecognizerResultItem(category: Category) {
        Column {
            Text(text = category.categoryName() ?: "")
            Text(text = category.score()?.toString() ?: "")
        }
    }

    @Composable
    fun ViewHolder(label: String?, score: Float?) {
        Column {
            Text(
                text = label ?: NO_VALUE,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = if (score != null) String.format(Locale.US, "%.2f", score) else NO_VALUE,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }

    companion object {
        private const val NO_VALUE = "--"
    }

}
