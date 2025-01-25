package com.example.scheduleappui

import androidx.annotation.DrawableRes

data class Dummy(
    @DrawableRes val icon : Int,
    val name : String
)

val libraries = listOf<Dummy>(
    Dummy(R.drawable.ic_playlist_green, "V.I.P Entrance"),
    Dummy(R.drawable.ic_microphone,"Main Entrance"),
    Dummy(R.drawable.ic_baseline_album_24,"Main Entrance"),
    Dummy(R.drawable.ic_baseline_music_note_24,"Main Entrance"),
    Dummy(R.drawable.baseline_dangerous_24,"Restricted")

)

val shiftsLibrary = listOf<Dummy>(
    Dummy(R.drawable.baseline_sunny_24, "Day Shift"),
    Dummy(R.drawable.baseline_light_24, "Evening Shift"),
    Dummy(R.drawable.baseline_nightlight_round_24, "Night Shift")
)
