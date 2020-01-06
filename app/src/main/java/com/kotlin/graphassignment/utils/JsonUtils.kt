package com.kotlin.graphassignment.utils

import android.content.Context
import org.json.JSONObject

object JsonUtils {

    fun loadJSONFromAsset(context: Context, fileName: String): JSONObject {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return JSONObject(String(buffer, Charsets.UTF_8))
    }
}