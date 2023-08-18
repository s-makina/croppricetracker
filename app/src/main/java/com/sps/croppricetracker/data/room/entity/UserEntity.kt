package com.sps.croppricetracker.data.room.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "tbl_user")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val token: String?,
    val code: String,
    val last_logged: Boolean = false
//    val roles: List<Role>
)