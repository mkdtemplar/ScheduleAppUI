package com.example.scheduleappui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleappui.ui.theme.AccountView
import com.example.scheduleappui.ui.theme.AnnualLeaveRequestScreen
import com.example.scheduleappui.ui.theme.AppSwitcher
import com.example.scheduleappui.ui.theme.Assignments
import com.example.scheduleappui.ui.theme.AssignmentsLibraryBrowse
import com.example.scheduleappui.ui.theme.Home
import com.example.scheduleappui.ui.theme.MainView
import com.example.scheduleappui.ui.theme.PositionsBottomScreen
import com.example.scheduleappui.ui.theme.Positions
import com.example.scheduleappui.ui.theme.Shift

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, pd : PaddingValues){

    NavHost(navController = navController,
        startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd) ){

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Positions.route){
            Positions()
        }

        composable(Screen.DrawerScreen.Shift.route) {
            Shift()
        }
        composable(Screen.DrawerScreen.Assigments.route) {
            Assignments()
        }

        composable(Screen.BottomScreen.Home.bRoute) {
            Home()
        }

        composable(Screen.BottomScreen.Assignments.bRoute) {
            Assignments()
        }

        composable(Screen.BottomScreen.Positions.bRoute) {
            Positions()
        }

        composable("al") {
            AnnualLeaveRequestScreen()
        }

    }

}
