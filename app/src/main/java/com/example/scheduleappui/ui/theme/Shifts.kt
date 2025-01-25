package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.Dummy
import com.example.scheduleappui.shiftsLibrary

@Composable
fun Shifts() {
    LazyColumn() {
        items(shiftsLibrary) {
            sh -> ShiftItem(sh)
        }
    }
}

@Composable
fun ShiftItem(shift : Dummy) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(painter = painterResource(id = shift.icon),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    contentDescription = shift.name)
                Text(text = shift.name)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow Right")
        }
        Divider(color = Color.LightGray)
    }
}