package com.pgkchamps.net.common

import com.pgkchamps.net.api.UserApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val ret = Retrofit.Builder()      // Настройка работы ретрофит (Диспетчер)
    .baseUrl("https://otbor.api.alspio.com/api/")       // Главный базовый адрес вашего сервера.
    .addConverterFactory(GsonConverterFactory.create())     // Переводчика GSON (из JSON в data class)
    .client(OkHttpClient())     // Проводник запросов на сервер
    .build()      // Собрать все вместе

val userApi = ret.create<UserApiInterface>() // Путь к файлу с инструкцией ретрофиту