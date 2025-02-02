package com.example.scheduleappui.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scheduleappui.AuthViewModel
import com.example.scheduleappui.R
import com.example.scheduleappui.Result


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onSignInSuccess:  () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Login Screen", textAlign = TextAlign.Center)
            },
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.app_bar_color),
                    titleContentColor = colorResource(id = R.color.white),
                ),
            )
        }
    ){
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val result by authViewModel.authResult.observeAsState()
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Password")},
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    authViewModel.login(email, password)
                    when (result) {
                        is Result.Success -> {
                            onSignInSuccess()
                        }
                        is Result.Error -> {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Login")
            }
        }
    }
}
