package com.example.gravitytest

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentUtil {
    fun swapFragments(
        fragmentManager: FragmentManager?,
        containerViewId: Int,
        newFragment: Fragment,
    ) {
        if (fragmentManager == null) return

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, newFragment);
        fragmentTransaction.commit()
    }
}