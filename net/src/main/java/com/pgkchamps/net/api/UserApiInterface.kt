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

interface UserApiInterface {

    @POST("collections/users/records")
    suspend fun reg(@Body body: RegDto): Response<Unit>

    @POST("collections/users/auth-with-password")
    suspend fun auth(@Body body: UserLogin): Response<UserLoginResponse>

    @GET("collections/users/records/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<UserProfile>


}