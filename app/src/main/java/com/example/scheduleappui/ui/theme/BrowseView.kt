package com.example.scheduleappui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import com.example.scheduleappui.R

@Composable
fun Browse() {
    val categories = listOf("Workout", "Taekwondo", "Kickboxing", "Metal")
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories) {
            cat ->
            BrowserItem(cat, drawable = R.drawable.baseline_apps_24)
        }
    }
}