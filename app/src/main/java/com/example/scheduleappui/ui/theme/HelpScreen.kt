package com.example.scheduleappui.ui.theme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.sendmail.SendEmail
import com.example.scheduleappui.userpreferences.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen() {
    var messageValue by remember { mutableStateOf("") }
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = messageValue,
            onValueChange = { messageValue = it },
            label = { Text("Describe problem here") },
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(8.dp),
            singleLine = false,
            maxLines = 10,
            minLines = 3
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {
                if (messageValue.isNotEmpty()) {
                    SendEmail(context, UserPreferences.getEmail(context).toString(),
                        "Report send\n$messageValue"
                        )
                }
            }
        ) {
            Text("Submit")
        }
    }
}