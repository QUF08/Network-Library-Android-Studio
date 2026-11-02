package com.pgkchamps.net.common

import com.pgkchamps.net.api.UserApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val ret = Retrofit.Builder()
    .baseUrl("https://otbor.api.alspio.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()

val userApi = ret.create<UserApiInterface>()