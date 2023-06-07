package com.sps.compose.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject

fun log(content: Any?) {
    Log.i("BETH", "$content")
}

fun toast(context: Context, message: String?) {
    Toast.makeText(context, "$message", Toast.LENGTH_LONG).show()
}

