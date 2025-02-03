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
import com.example.scheduleappui.ui.theme.PositionsBottomScreen
import com.example.scheduleappui.ui.theme.Positions
import com.example.scheduleappui.ui.theme.Shift

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, pd : PaddingValues){

    NavHost(navController = navController,
        startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd) ){

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

        composable(Screen.BottomScreen.Positions.bRoute) {
            PositionsBottomScreen()
        }

    }

}
