package com.example.gravitytest.models

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val APP_PREFERENCES = "myData"
    private val HOME_Path = "homePath"
    private val START_BOOl = "first_time"
    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor {
        return preferences.edit()
    }

    fun getHomePath(): String? {
        return preferences.getString(HOME_Path, null)
    }

    fun getInfoAboutStart():Boolean{
        return preferences.getBoolean(START_BOOl, true)
    }

    fun editStartInfo(data:Boolean){
        getEditor().putBoolean(START_BOOl, data).commit()
    }

    fun editHomePath(path:String){
        getEditor().putString(HOME_Path, path).commit()
    }
}