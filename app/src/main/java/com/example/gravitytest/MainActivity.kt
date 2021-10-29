package com.example.gravitytest

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.lang.Error
import android.widget.Toast

import android.content.IntentFilter
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MainActivity : AppCompatActivity() {
    val APP_PREFERENCES = "myData"
    private lateinit var prefs: SharedPreferences
    private val HOME_Path = "homePath"
    private val BROADCAST_ACTION = "Path_is_there"

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        prefs =
            getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        FragmentUtil().swapFragments(supportFragmentManager, R.id.fragmentContainer, LoadFragment())

    }







}