package com.samuel.movilplag_e

import android.content.Context

class SharedData {
    fun GoogleId(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("google_id", "") ?: ""
    }
    fun clean(context: Context){
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}