package com.pgkchamps.net.common.ret

import com.pgkchamps.net.storage.UserDataStrorageImpl
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = UserDataStrorageImpl().getToken()

        return chain.proceed(
            chain.request().newBuilder().addHeader("Authorization","Bearer $token").build()
        )
    }

}