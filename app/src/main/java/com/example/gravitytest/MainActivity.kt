package com.example.gravitytest

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.lang.Error

class MainActivity : AppCompatActivity() {
    val APP_PREFERENCES = "myData"
    lateinit var fragmentManager:FragmentManager
    private lateinit var prefs: SharedPreferences
    private val HOME_Path = "homePath"

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        prefs =
            getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        val fragment = LoadFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, fragment)
        transaction.commit()

        if (savedInstanceState == null){
            val path = if (isFirstTime())
                DataLoader().getLink()
            else
                loadHomePath()

            loadHomeFragment(path)
        }
    }

    private fun loadHomePath(): String {
        var path = prefs.getString(HOME_Path, null)
        if ( path != null){
            return path
        }
        path = DataLoader().getHome()
        val editor = prefs.edit()
        editor.putString(HOME_Path, path)
        editor.apply()
        return path
    }

    private fun loadHomeFragment(path:String){
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString("path", path)
        fragment.arguments = bundle
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun isFirstTime():Boolean {
        prefs = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return if (prefs.getBoolean("first_time", true)) {
            val editor = prefs.edit()
            editor.putBoolean("first_time", false)
            editor.apply()
            true
        } else false
    }

}