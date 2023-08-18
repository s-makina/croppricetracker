package com.sps.croppricetracker.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val token: String?,
    val code: String,
    val roles: List<Role>
)