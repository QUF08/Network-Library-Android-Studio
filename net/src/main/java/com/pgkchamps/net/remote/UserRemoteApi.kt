package com.pgkchamps.net.remote

import android.util.Log
import com.pgkchamps.net.api.UserApiInterface
import com.pgkchamps.net.common.userApi
import com.pgkchamps.net.dto.RegDto
import com.pgkchamps.net.dto.UserLogin
import com.pgkchamps.net.dto.UserLoginResponse
import com.pgkchamps.net.dto.UserProfile

class UserRemoteApi(
    val api: UserApiInterface = userApi
) {

    suspend fun reg(regDto: RegDto): Boolean {
        try {

            val res = api.reg(regDto)

            if (!res.isSuccessful) {
                val res2 = res.errorBody().toString()
                Log.i("reg",res2)
                Log.i("reg",res.toString())
                Log.i("reg",res.message())
                Log.i("reg",res.code().toString())
                return false
            }

            return true

        } catch (e: Exception) {
            throw Exception("что-то пошло не так ошибка $e ")
        }
    }

    suspend fun auth( userLogin: UserLogin): UserLoginResponse {
        try {

            val res = api.auth(userLogin)

            if (!res.isSuccessful) {
                throw Exception("что-то пошло не так")
            }

            return res.body()!!

        } catch (e: Exception) {
            throw Exception("что-то пошло не так ошибка $e ")
        }
    }

    suspend fun profileGet(id: String): UserProfile {
        try {

            val res = api.getProfile(id)

            if (!res.isSuccessful) {

                throw Exception("что-то пошло не так")

            }

            return res.body()!!

        } catch (e: Exception) {

            throw Exception("что-то пошло не так ошибка $e ")
        }
    }

}