package com.sps.croppricetracker.data.room

import com.sps.croppricetracker.data.model.User
import com.sps.croppricetracker.data.retrofit.dto.UserDto
import com.sps.croppricetracker.data.room.entity.UserEntity

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        token = token,
        code = code,
        roles = roles
    )
}
fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        token = token,
        code = code,
        roles = emptyList()
    )
}

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone,
        token = token,
        code = code,
//        roles = roles
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone,
        token = token,
        code = code,
//        roles = roles
    )
}
