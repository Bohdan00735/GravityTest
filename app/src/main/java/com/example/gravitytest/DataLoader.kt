package com.example.gravitytest

import android.app.Activity
import android.content.Intent
import android.os.SystemClock
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class DataLoader {
    private  val path = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod"

    fun getLink(activity: Activity, broadcastAction: String){
        val intent = Intent(broadcastAction)
        intent.putExtra("path", getJSON().getString("link"))

        activity.sendBroadcast(intent)
    }
    fun getHome(activity: Activity, broadcastAction: String){
        val intent = Intent(broadcastAction)
        intent.putExtra("type", "home")
        intent.putExtra("path", getJSON().getString("home"))
        activity.sendBroadcast(intent)
    }
    private fun getJSON():JSONObject{
        var jsonObject:JSONObject? = null
        val thread = Thread{
            try {
                jsonObject = JSONObject(URL(path).readText())
            }catch (e: Exception){
                e.printStackTrace()
                throw Exception("Json didn`t load")
            }
        }
        thread.start()
        thread.join()
        return jsonObject!!
    }
}