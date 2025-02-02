package com.example.scheduleappui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleappui.AuthViewModel

@Composable
fun AppSwitcher() {
    var isLoggedIn by remember { mutableStateOf(false) }
    val authViewModel : AuthViewModel = viewModel()

    if (isLoggedIn == false) {
        LoginScreen(authViewModel) {
            isLoggedIn = true
        }
    } else {
        MainView()
    }
}