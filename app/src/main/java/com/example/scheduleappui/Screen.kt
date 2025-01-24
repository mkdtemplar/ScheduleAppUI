package com.example.scheduleappui

import androidx.annotation.DrawableRes

sealed class Screen(val title : String, val route : String) {
    sealed class DrawerScreen(val dTitle : String, val dRoute : String, @DrawableRes val icon : Int) :
            Screen(dTitle, dRoute) {
                object Account : DrawerScreen("Account", "account",
                    R.drawable.ic_account)
                object Subscription : DrawerScreen("Subscription", "subscribe",
                    R.drawable.ic_subscribe
                    )
                object AddAccount : DrawerScreen("Add Account", "add_account",
                    R.drawable.baseline_person_add_alt_1_24
                    )
                object LoginScreen : DrawerScreen("Login", "login",
                    R.drawable.baseline_login_24)
            }
    sealed class BottomScreen(val bTitle : String, val bRoute : String, @DrawableRes val icon : Int) :
            Screen(bTitle, bRoute) {
                object Home : BottomScreen("Home", "home",
            R.drawable.baseline_music_video_24)
                object Library : BottomScreen("Library", "library",
            R.drawable.baseline_library_music_24)
                object Browse : BottomScreen("Browse", "browse",
            R.drawable.baseline_article_24)
            }
}

val screenInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount,
    Screen.DrawerScreen.Account
)