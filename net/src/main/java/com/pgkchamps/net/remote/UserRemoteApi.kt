package com.pgkchamps.net.remote

import com.pgkchamps.net.api.UserApiInterface
import com.pgkchamps.net.common.ret.userApi
import com.pgkchamps.net.dto.RegDto
import com.pgkchamps.net.dto.UserLogin
import com.pgkchamps.net.dto.UserLoginResponse
import com.pgkchamps.net.dto.UserProfile
import android.util.Log
import com.pgkchamps.net.storage.UserDataStrorageImpl

// Этот класс — "Менеджер", который берет инструкции Ретрофита и безопасно выполняет их, защищая приложение от вылетов
class UserRemoteApi(
    // Менеджер держит при себе инструмент связи (Ретрофит-интерфейс)
    val api: UserApiInterface = userApi
) {

    // 1. ФУНКЦИЯ РЕГИСТРАЦИИ (Возвращает true — если успешно, false — если ошибка)
    suspend fun reg(regDto: RegDto): Boolean {
        try {
            // Менеджер берет коробку regDto и отправляет её через ретрофит-штекер
            val res = api.reg(body = regDto)

            // Если сервер ответил ошибкой (например, email уже занят или код 400/500)
            if (!res.isSuccessful) {
                // Менеджер подробно записывает в логи (Logcat) всю инфу от сервера, чтобы программист понял, в чем косяк
                val res2 = res.errorBody()?.toString()
                Log.i("reg", res2 ?: "пустая ошибка")
                Log.i("reg", res.toString())
                Log.i("reg", res.message())
                Log.i("reg", res.code().toString())

                return false // Регистрация не удалась
            }
            return true // Всё супер, сервер ответил успехом!
        } catch (e: Exception) {
            // Если вообще пропал интернет или сервер упал — перехватываем эту жесткую ошибку, чтобы приложение не крашнулось
            throw Exception("что-то пошло не так ошибка $e")
        }
    }

    // 2. ФУНКЦИЯ ВХОДА/АВТОРИЗАЦИИ (Возвращает готовую DTO-коробку с Токеном от сервера)
    suspend fun auth(userLogin: UserLogin): UserLoginResponse {
        try {
            // Отправляем посылку с логином/паролем на сервер
            val res = api.auth(body = userLogin)

            // Если сервер сказал "Пароль неверный" или "Юзера нет"
            if (!res.isSuccessful) {
                throw Exception("что-то пошло не так")
            }
            // Если всё успешно — берем чистый ответ из тела (body) сервера и отдаем его приложению
            // Оператор !! означает: "Я на 100% уверен, что там есть данные, распаковывай"

            UserDataStrorageImpl().saveToken(res.body()!!.token)

            return res.body()!!

        } catch (e: Exception) {
            // Ловим сетевые сбои
            throw Exception("что-то пошло не так ошибка $e")
        }
    }

    // 3. ФУНКЦИЯ ПОЛУЧЕНИЯ ПРОФИЛЯ ПО ID (Возвращает коробку UserProfile)
    suspend fun getProfile(id: String): UserProfile {
        try {
            // Передаем в штекер ID нужного пользователя
            val res = api.getProfile(id)

            // Если сервер не нашел такого юзера
            if (!res.isSuccessful) {
                throw Exception("что-то пошло не так")
            }

            // Возвращаем приложению данные профиля (email и name)
            return res.body()!!

        } catch (e: Exception) {
            throw Exception("что-то пошло не так ошибка $e")
        }
    }
}
