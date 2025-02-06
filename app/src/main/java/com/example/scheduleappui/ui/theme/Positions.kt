package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleappui.PositionViewModel
import com.example.scheduleappui.screen.PositionItem

@Composable
fun Positions() {
    val positionViewModel : PositionViewModel = viewModel()
    val positions by positionViewModel.positions.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var positionDetails by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Positions", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
            Column(modifier = Modifier.padding(8.dp)) {
                LazyColumn {
                    items(positions){
                            position -> PositionItem(
                        position,
                        onClickOpenDetails = {
                           positionDetails = position.name
                            showDialog = true
                        }
                    )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                        }
                        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                    }

                }
            }
            if (showDialog) {
                AlertDialogPosition(positionDetails) {
                    showDialog = false
                }
            }
        }
    }
}

@Composable
fun AlertDialogPosition(positionDetails : String, onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {Text(text = "Position details")},
            text = {
                Column(modifier = Modifier.height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Box( modifier = Modifier
                        .wrapContentHeight()
                        .padding(8.dp)) {
                        Text(text = positionDetails)
                    }
                }
            },
            buttons = {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center ) {
                    Button(onClick = onDismiss) {
                        Text("Dismiss", fontStyle = FontStyle.Italic, textAlign = TextAlign.Center)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().
            background(MaterialTheme.colorScheme.background).padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
}