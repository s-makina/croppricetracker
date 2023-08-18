 package com.sps.croppricetracker.data.room

import androidx.room.TypeConverter

class Convertors {
    @TypeConverter
    fun arrayToString(list: List<String>): String {
        val ls = list.joinToString(separator = ",") { it }
        return list.joinToString(separator = ",") { it }
    }

    @TypeConverter
    fun arrayToString(text: String): List<String> {
        return text.split(",")
    }
}