package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Positions() {
    Column(
        modifier = Modifier.height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Positions")
        Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
            Column(modifier = Modifier.padding(8.dp)) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "V.I.P Entrance", textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
                        Row() {
                            TextButton(onClick = {}) {
                                Row {
                                    Text(text = "Employees assigned")
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = ""
                                    )
                                }
                            }

                        }
                    }
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Main Entrance", textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
                        Row() {
                            TextButton(onClick = {}) {
                                Row {
                                    Text(text = "Employees assigned")
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = ""
                                    )
                                }
                            }

                        }
                    }
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Restricted", textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
                        Row() {
                            TextButton(onClick = {}) {
                                Row {
                                    Text(text = "Employees assigned")
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = ""
                                    )
                                }
                            }

                        }
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
