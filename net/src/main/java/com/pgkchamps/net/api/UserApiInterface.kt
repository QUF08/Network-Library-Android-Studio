package com.pgkchamps.net.api

import com.pgkchamps.net.dto.RegDto
import com.pgkchamps.net.dto.UserLogin
import com.pgkchamps.net.dto.UserLoginResponse
import com.pgkchamps.net.dto.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiInterface {   // Это чистая инструкция. В нем нет никакого исполняемого кода, только правила взаимодействия.

         //Аннотации REST — это архитектурный стиль для API (HTTP-методы)

 // POST - отправка данных на сервер (сайт)
    @POST("collections/users/records")    // путь
    suspend fun reg(@Body body: RegDto): Response<Unit>  // Регистрация пользователя (создание акка)

    @POST("collections/users/auth-with-password")
    suspend fun auth(@Body body: UserLogin): Response<UserLoginResponse> // Аутентификация пользователя (вход в акк)

 // GET - получение данных с сервера (сайта)
    @GET("collections/users/records/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<UserProfile>  // Поделиться профилем пользователя с помощью id
}