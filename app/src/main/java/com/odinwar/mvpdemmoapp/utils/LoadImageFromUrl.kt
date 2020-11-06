package com.odinwar.mvpdemmoapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL

class LoadImageFromUrl(private val imageView: ImageView) : AsyncTask<String, Unit, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(params[0].toString())
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connectTimeout = TIME_OUT
            httpURLConnection.readTimeout = TIME_OUT
            httpURLConnection.requestMethod = METHOD_GET
            httpURLConnection.doOutput = true
            httpURLConnection.connect()
            bitmap = BitmapFactory.decodeStream(url.openStream())
            httpURLConnection.disconnect()
            return Bitmap.createScaledBitmap(bitmap, 200, 200, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        result?.let {
            imageView.setImageBitmap(result)
        }
        super.onPostExecute(result)
    }

    companion object {
        const val TIME_OUT = 20000
        const val METHOD_GET = "GET"
    }
}
