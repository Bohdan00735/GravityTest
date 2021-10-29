package com.example.gravitytest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.content.SharedPreferences

import android.preference.PreferenceManager
import android.widget.Toast
import java.lang.Error
import java.util.prefs.Preferences


class LoadFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_load, container, false)
        view.findViewById<ImageView>(R.id.loadImage)
            .startAnimation(AnimationUtils.loadAnimation(context,R.anim.rotate_anim))

        return view
    }



}