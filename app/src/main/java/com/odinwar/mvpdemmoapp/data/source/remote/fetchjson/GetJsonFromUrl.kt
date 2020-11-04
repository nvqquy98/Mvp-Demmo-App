@file:Suppress("DEPRECATION")

package com.odinwar.mvpdemmoapp.data.source.remote.fetchjson

import android.os.AsyncTask
import com.odinwar.mvpdemmoapp.data.source.remote.OnFetChDataJsonListener

class GetJsonFromUrl<T>(
    private val listener: OnFetChDataJsonListener<T>,
    private val keyEntity: String
) : AsyncTask<String?, Void?, String?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String? {
        var data = ""
        try {
            data = HandlingJson().getJsonFromUrl(params[0].toString())
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(result: String?) {
        if (!(result == null || result.isBlank())) {
            val data = HandlingJson().parseJsonToData(result, keyEntity)
            @Suppress("UNCHECKED_CAST")
            listener.onSuccess(data as T)
        } else listener.onError(exception)
        super.onPostExecute(result)
    }
}
