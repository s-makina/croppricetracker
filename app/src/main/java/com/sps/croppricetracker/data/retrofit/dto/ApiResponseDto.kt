package com.sps.croppricetracker.data.retrofit.dto

import com.google.gson.JsonObject
import androidx.annotation.Keep

@Keep
data class ApiResponseDto(
    val message: String,
    val error: String?,
    val errors: JsonObject?,
    val data: Any
)