package com.example.gravitytest.views

import org.w3c.dom.Text

interface LoadView {
    fun loadHomeFragment(path:String)
    fun showError(message:String)
}