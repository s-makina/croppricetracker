package com.sps.croppricetracker.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GsonUtil {
    inline fun <reified T> parseObject(data: JsonObject, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson(data, typeToken)
    }

    inline fun <reified T> parseObject(data: Any, typeToken: Type): T {
        val json: String = Gson().toJson(data)
        val gson = GsonBuilder().create()
        return gson.fromJson(json, typeToken)
    }

    inline fun <reified T> fromJson(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson(json, typeToken)
    }

    fun toJson(obj: Any): String {
        return Gson().toJson(obj)
    }

    //        inline fun <reified T> toArray(json: Any) = Gson().fromJson<T>(Gson().toJson(json), object : TypeToken<T>() {}.type)
    inline fun <reified T> Gson.toArray(json: Any) =
        fromJson<T>(Gson().toJson(json), object : TypeToken<T>() {}.type)

    fun processErrors(jsonObject: JsonObject?): String {
        var formattedError = ""
        jsonObject?.keySet()?.forEach { key ->
            var errString = ""
            val errors = jsonObject.getAsJsonArray(key)
            errors.forEach {
                errString += it.asString
            }
            formattedError += "$errString \n"
        }
        return formattedError
    }
}