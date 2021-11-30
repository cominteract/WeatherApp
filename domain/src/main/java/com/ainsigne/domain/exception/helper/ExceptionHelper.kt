package com.ainsigne.domain.exception.helper

import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.Exception

fun ResponseBody?.extractErrorDetail(): String? {
    val response = this?.string()
    return try {
        val objError = JSONObject(response ?: "No error message!")
        objError.getJSONObject("errors").getString("detail")
    } catch (e: Exception) {
        "Error Parsing Unsuccessful!"
    }
}
