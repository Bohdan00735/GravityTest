package com.example.gravitytest.views

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import com.example.gravitytest.FragmentUtil
import com.example.gravitytest.R


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