package com.odinwar.mvpdemmoapp.data.source.remote.fetchjson

import com.odinwar.mvpdemmoapp.data.model.ConstantsModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HandlingJson {

    @Throws(Exception::class)
    fun getJsonFromUrl(stringUrl: String): String {
        val url = URL(stringUrl)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = TIME_OUT
        httpURLConnection.readTimeout = TIME_OUT
        httpURLConnection.requestMethod = METHOD_GET
        httpURLConnection.doOutput = true
        httpURLConnection.connect()
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToArray(result: String, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            val jsonArray: JSONArray? = JSONArray(result)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val jsonObjects = jsonArray?.getJSONObject(i)
                val item = parseJsonToObject(jsonObjects, keyEntity)
                item?.let { data.add(item) }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return data
    }

    private fun parseJsonToObject(result: JSONObject?, keyEntity: String): Any? {
        try {
            result?.let {
                when (keyEntity) {
                    ConstantsModel.PHOTO ->
                        return ParseJsonObject().pareJsonToPhoto(result)
                    else -> null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private const val TIME_OUT = 60000
        private const val METHOD_GET = "GET"
    }
}
