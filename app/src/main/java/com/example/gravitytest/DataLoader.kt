package com.example.gravitytest

import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class DataLoader {
    private  val path = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod"

    fun getLink(): String{
        return getJSON().getString("link")
    }
    fun getHome(): String{
        return getJSON().getString("home")
    }
    fun getJSON():JSONObject{
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