package com.example.scheduleappui.userpreferences

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {
    private const val PREF_NAME = "user_preferences"
    private const val EMAIL_KEY = "user_email"

    private fun getPreferences(context: Context) : SharedPreferences {
       return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveEmail(context: Context, email : String) {
        val preferences = getPreferences(context)
        preferences.edit().putString(EMAIL_KEY, email).apply()
    }

    fun getEmail(context: Context) : String? {
        val preferences = getPreferences(context)
        return preferences.getString(EMAIL_KEY, null)
    }

    fun clearEmail(context: Context) {
        val preferences = getPreferences(context)
        preferences.edit().remove(EMAIL_KEY).apply()
    }
}