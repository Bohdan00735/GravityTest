package com.example.gravitytest.models

import android.app.Activity
import android.content.Intent
import com.example.gravitytest.Exceptions.JSONNotLoadedException
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class ServerModel {
    private  val path = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod"

    fun getLink(): String? {
        return getJSON()?.getString("link")

    }
    fun getHome(): String? {
        return getJSON()?.getString("home")
    }
    private fun getJSON(): JSONObject? {
        var jsonObject: JSONObject? = null
        val thread = Thread{
            try {
                jsonObject = JSONObject(URL(path).readText())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        thread.start()
        thread.join()
        return jsonObject
    }
}