package com.example.scheduleappui

import androidx.annotation.DrawableRes

sealed class Screen(val title : String, val route : String) {
    sealed class DrawerScreen(val dTitle : String, val dRoute : String, @DrawableRes val icon : Int) :
            Screen(dTitle, dRoute) {
                object Account : DrawerScreen("Account", "account",
                    R.drawable.ic_account)
                object Position : DrawerScreen("Position", "position",
                    R.drawable.ic_subscribe
                    )
                object Shift : DrawerScreen("Shift", "shift",
                    R.drawable.baseline_person_add_alt_1_24
                    )

            }
    sealed class BottomScreen(val bTitle : String, val bRoute : String, @DrawableRes val icon : Int) :
            Screen(bTitle, bRoute) {
                object Home : BottomScreen("Home", "home",
            R.drawable.baseline_music_video_24)
                object Library : BottomScreen("Positions", "Positions",
            R.drawable.baseline_library_music_24)
                object Browse : BottomScreen("Assignments", "assignments",
            R.drawable.baseline_article_24)
            }
    object HomeScreen : Screen("MainScreen", "main-screen")
    object LoginScreen : Screen("Login", "login")
}

val screenInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Position,
    Screen.DrawerScreen.Shift,
    Screen.DrawerScreen.Account
)