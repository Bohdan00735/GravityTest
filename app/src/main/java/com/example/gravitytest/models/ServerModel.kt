package com.example.gravitytest.models

import android.content.ContentProviderOperation.newCall
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.net.URL

class ServerModel {
    private  val url = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod"

    fun getLink(): String? {
        return getJSON()?.getString("link")

    }
    fun getHome(): String? {
        return getJSON()?.getString("home")
    }
    private fun getJSON(): JSONObject? {

        val request: Request = Request.Builder().url(url).build()
        val okHttpClient = OkHttpClient()
        val response = okHttpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            return null
        }
        return JSONObject(response.body()?.string()!!)
    }
}