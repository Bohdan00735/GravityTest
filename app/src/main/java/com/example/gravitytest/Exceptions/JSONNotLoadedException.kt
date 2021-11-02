package com.example.gravitytest.Exceptions

import java.lang.Exception

class JSONNotLoadedException : Exception() {
    override val message: String
        get() = "Json didn`t load"
}