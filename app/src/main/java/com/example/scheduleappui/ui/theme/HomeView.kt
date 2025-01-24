package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val categories = listOf("Workout", "Taekwondo", "Kickboxing", "Metal")
    val gruped = listOf<String>("New releases", "Favorites", "Top rated").groupBy { it[0] }

    LazyColumn {
        gruped.forEach {
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(categories) {
                        cat ->
                        BrowserItem(cat, R.drawable.baseline_apps_24)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(category : String, drawable : Int) {
    Card(modifier = Modifier.padding(16.dp).size(200.dp),
        border = BorderStroke(3.dp, color = Color.DarkGray)) {

        Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(text = category)
            Image(painter = painterResource(id = drawable), contentDescription = category)
        }
    }
}