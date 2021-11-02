package com.example.gravitytest.useCases

import android.app.Activity
import android.content.Context
import com.example.gravitytest.models.Preferences
import com.example.gravitytest.models.ServerModel

class DataLoadUseCase {

    fun isFirstTime(preferences: Preferences):Boolean {
        return if (!preferences.getInfoAboutStart()) {
            preferences.editStartInfo(true)
            true
        } else false
    }
}