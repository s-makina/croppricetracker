package com.sps.croppricetracker.data.retrofit

import com.sps.croppricetracker.data.retrofit.dto.ApiResponseDto
import retrofit2.http.*

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("login")
    suspend fun authenticate(
        @Field("username") phone: String,
        @Field("password") password: String,
    ): ApiResponseDto

    @FormUrlEncoded
    @POST("register")
    suspend fun signup(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): ApiResponseDto
}