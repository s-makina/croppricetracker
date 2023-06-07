package com.sps.compose.data.retrofit

import com.sps.compose.data.Prefs
import okhttp3.Interceptor

class OAuthInterceptor(private val tokenType: String ): Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", "$tokenType ${Prefs.authUser.value?.token}").build()
        return chain.proceed(request)
    }
}

