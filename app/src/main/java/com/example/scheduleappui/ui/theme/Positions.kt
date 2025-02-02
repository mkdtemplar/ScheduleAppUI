package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleappui.PositionViewModel
import com.example.scheduleappui.screen.PositionItem

@Composable
fun Positions() {
    val positionViewModel : PositionViewModel = viewModel()
    val positions by positionViewModel.positions.observeAsState(emptyList())

    positionViewModel.loadPositions()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Positions")
        Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
            Column(modifier = Modifier.padding(8.dp)) {
                LazyColumn {
                    items(positions){
                            position -> PositionItem(position)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                        }
                        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                    }

                }
               Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Row(Modifier.padding(vertical = 16.dp)) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Get Position info"
                        )
                        Text(text = "Get Position info", modifier = Modifier.clickable{},
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
