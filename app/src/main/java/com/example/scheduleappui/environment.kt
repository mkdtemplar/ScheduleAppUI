package com.example.scheduleappui

import android.os.Build
import androidx.annotation.RequiresApi
import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.nio.file.Paths

@RequiresApi(Build.VERSION_CODES.O)
fun GetEnv () : String? {
    val envFile = Paths.get("app", ".env").toFile()
    val envVar = dotenv {
        directory = envFile.toPath().parent.toString()
        filename = envFile.name
    }

    return envVar["APP_ID"] ?: ""
}



