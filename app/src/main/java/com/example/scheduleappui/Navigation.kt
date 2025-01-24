package com.example.scheduleappui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.example.scheduleappui.ui.theme.Subscription

@Composable
fun Navigation(navController: NavHostController, authViewModel: AuthViewModel, viewModel: MainViewModel, pd:PaddingValues){

    NavHost(navController = navController,
        startDestination = Screen.DrawerScreen.LoginScreen.route, modifier = Modifier.padding(pd) ){

        composable(Screen.DrawerScreen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onSignInSuccess = {
                    navController.navigate(Screen.DrawerScreen.Account.route)
                }
            )
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            Subscription()
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
