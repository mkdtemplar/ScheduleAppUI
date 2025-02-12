package com.example.scheduleappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.Dummy
import com.example.scheduleappui.shiftsLibrary
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.example.scheduleappui.dayShiftStaff
import com.example.scheduleappui.eveningShiftStaff
import com.example.scheduleappui.nightShiftStaff

@Composable
fun Shifts() {
    var showDialog by remember { mutableStateOf(false) }
    var shiftDetails by remember { mutableStateOf("") }

    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        LazyColumn() {
            items(shiftsLibrary) {
                    sh -> ShiftItem(sh, onClickEvent = {
                        shiftDetails = sh.name
                        showDialog = true
            })
            }
        }

        if (showDialog) {
            ShiftAlertDialog(shiftDetails) {
                showDialog = false
            }
        }
    }
}

@Composable
fun ShiftItem(shift : Dummy, onClickEvent : () -> Unit) {

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
            TextButton(onClick = {
                onClickEvent()
            }) {
                Row {
                    Text(text = "Employees assigned")
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "See All Employees assigned"
                    )
                }
            }
        }
        Divider(color = Color.LightGray)
    }
}

@Composable
fun ShiftAlertDialog(shift : String, onDismiss : () -> Unit) {
    var selectedStaff by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedShift by remember { mutableStateOf("") }


    fun showStaffByShift() : List<String> {
        selectedShift = shift
        selectedStaff = when (shift) {
            "Day Shift" -> dayShiftStaff
            "Evening Shift" -> eveningShiftStaff
            "Night Shift" -> nightShiftStaff
            else -> emptyList()
        }

        return selectedStaff
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "$shift Staff") },
        text = {
            Column(modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                showStaffByShift().forEach {
                    staff ->
                    Box( modifier = Modifier
                        .wrapContentHeight()
                        .padding(8.dp)) {
                        Text(text = staff)
                    }
                    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
        },
        buttons = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center ) {
                Button(onClick = onDismiss) {
                   Text(
                        "Dismiss",
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    )
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

