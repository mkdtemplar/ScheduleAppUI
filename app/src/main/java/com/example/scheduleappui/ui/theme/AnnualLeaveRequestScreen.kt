package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.scheduleappui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnualLeaveRequestScreen() {

    Scaffold( topBar = {
        TopAppBar(title = {
            Text("Annual leave request", textAlign = TextAlign.Center)
        },
            colors = topAppBarColors(
                containerColor = colorResource(id = R.color.app_bar_color),
                titleContentColor = colorResource(id = R.color.white),
            ),
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}