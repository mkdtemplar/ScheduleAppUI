package com.example.scheduleappui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleappui.ui.theme.AccountView
import com.example.scheduleappui.ui.theme.Browse
import com.example.scheduleappui.ui.theme.Home
import com.example.scheduleappui.ui.theme.Library
import com.example.scheduleappui.ui.theme.LoginScreen
import com.example.scheduleappui.ui.theme.MainView
import com.example.scheduleappui.ui.theme.Positions
import com.example.scheduleappui.ui.theme.Shift

@Composable
fun Navigation(navController: NavHostController, authViewModel: AuthViewModel, viewModel: MainViewModel){
    var isLoggedIn by remember { mutableStateOf(false) }
    NavHost(navController = navController,
        startDestination = if (isLoggedIn) Screen.HomeScreen.route else Screen.LoginScreen.route){

        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onSignInSuccess = {
                    isLoggedIn = true
                   navController.navigate(Screen.HomeScreen.route) {
                       popUpTo(Screen.LoginScreen.route) {inclusive = true}
                   }
                }
            )
        }

        composable(Screen.HomeScreen.route) {
            MainView()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Position.route){
            Positions()
        }

        composable(Screen.DrawerScreen.Shift.route) {
            Shift()
        }

        composable(Screen.BottomScreen.Home.bRoute) {
            Home()
        }

        composable(Screen.BottomScreen.Browse.bRoute) {
            Browse()
        }

        composable(Screen.BottomScreen.Library.bRoute) {
            Library()
        }

    }

}
