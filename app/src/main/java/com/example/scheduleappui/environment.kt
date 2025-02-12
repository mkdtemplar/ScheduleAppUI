package com.example.scheduleappui

import io.github.cdimascio.dotenv.dotenv

fun GetEnv () : String? {
    val envVar = dotenv {
        directory = "./app/assets"
        filename = "env"
    }

    return envVar["APP_ID"] ?: ""
}

