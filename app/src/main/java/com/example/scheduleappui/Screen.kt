package com.example.scheduleappui

import androidx.annotation.DrawableRes

sealed class Screen(val title : String, val route : String) {
    sealed class DrawerScreen(val dTitle : String, val dRoute : String, @DrawableRes val icon : Int) :
            Screen(dTitle, dRoute) {
                object Account : DrawerScreen("Account", "account",
                    R.drawable.ic_account)
                object Positions : DrawerScreen("Positions", "positions",
                    R.drawable.ic_subscribe
                    )
                object Shift : DrawerScreen("Shift", "shift",
                    R.drawable.baseline_person_add_alt_1_24
                    )
        object Assigments : DrawerScreen("Assignments", "assignments",
            R.drawable.baseline_library_books_24
            )

            }
    sealed class BottomScreen(val bTitle : String, val bRoute : String, @DrawableRes val icon : Int) :
            Screen(bTitle, bRoute) {
                object Home : BottomScreen("Home", "home",
            R.drawable.baseline_music_video_24)
                object Positions : BottomScreen("Positions", "positions",
            R.drawable.baseline_library_music_24)
                object Assignments : BottomScreen("Assignments", "assignments",
            R.drawable.baseline_article_24)
            }
}

val screenInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Assignments,
    Screen.BottomScreen.Positions
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Positions,
    Screen.DrawerScreen.Shift,
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Assigments
)