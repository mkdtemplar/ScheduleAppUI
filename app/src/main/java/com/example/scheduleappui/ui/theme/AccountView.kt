package com.example.scheduleappui.ui.theme

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.scheduleappui.shiftsLibrary
import com.example.scheduleappui.userpreferences.UserPreferences

@Composable
fun AccountView(){
    var showDialog by remember { mutableStateOf(false) }
    var showDialogShifts by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(){
                Icon(imageVector= Icons.Default.AccountCircle ,
                    contentDescription = "Account",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Column {
                    Text("V.I.P Entrance")
                }
            }
            IconButton(onClick = {
                showDialog = true
            }){
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight
                    , contentDescription = null)
            }
        }

        Row(modifier = Modifier.padding(top = 16.dp).clickable {
            showDialogShifts = true
        }) {
            Icon(
                painter = painterResource(id = com.example.scheduleappui.R.drawable.baseline_music_video_24),
                contentDescription = "My Shifts",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "My Shifts")
        }
        Divider()
        if (showDialog) {
            AlertDialogAccount {
                showDialog = false
            }
        }

        if (showDialogShifts ) {
            AlertDialogShifts {
                showDialogShifts = false
            }
        }

    }
}

@Composable
fun AlertDialogAccount(onDismiss : () -> Unit) {
    val context: Context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { androidx.compose.material3.Text(text = "Account details") },
        text = {
            Column(modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Box( modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp)) {
                    Text(text = "User: ${UserPreferences.getEmail(context).toString()}")
                }
                Box( modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp)) {
                    Text(text = "Name: Operator 1")
                }
            }
        },
        buttons = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center ) {
                Button(onClick = onDismiss) {
                    androidx.compose.material3.Text(
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

@Composable
fun AlertDialogShifts(onDismiss: () -> Unit) {
    val shiftHours = listOf<String>(
        "08:00 - 16:00",
        "16:00 - 00:00",
        "00:00 - 08:00"
    )
    var i : Int = 0
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Shift details") },
        text = {
            Column(modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                shiftsLibrary.forEach {
                    shift ->
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(8.dp)
                    ) {
                        Text(text = "${shift.name} : ${shiftHours[i]}")
                    }
                    i++
                }
            }
        },
        buttons = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center ) {
                Button(onClick = onDismiss) {
                    androidx.compose.material3.Text(
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