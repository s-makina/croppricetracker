package com.sps.croppricetracker.data.retrofit.dto

import androidx.annotation.Keep
import com.sps.croppricetracker.data.model.Role

@Keep
class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val token: String?,
    val code: String,
    val my_credit: String,
    val credit: Double,
    val description: String?,
    val roles: List<Role>
)
