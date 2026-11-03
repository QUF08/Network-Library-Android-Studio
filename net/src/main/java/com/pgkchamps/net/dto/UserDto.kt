package com.pgkchamps.net.dto

   // DTO (Data Transfer Object) - выполняют роль чертежей для посылок.

//1. К ним обращается переводчик GSON (это главный потребитель этих классов)
//  • Когда вы отправляете запрос
//  • Когда сервер присылает ответ
//
//2. К ним обращается интерфейс Retrofit (UserApiInterface)
//  • Ретрофит видит это и понимает: «Так, в тело этого запроса мне нужно упаковать именно коробочку RegDto».
//  • Ретрофит понимает: «Когда сервер ответит, мне нужно будет выдать программисту готовую коробочку UserLoginResponse».


// 1. Посылка ОТ приложения К серверу для регистрации нового пользователя
data class RegDto(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val name: String,
)

// 2. Коробочка, в которую сервер упакует чистые данные профиля (при GET запросе)
data class UserProfile(
    val email: String,
    val name: String,
)

// 3. Посылка ОТ приложения К серверу для авторизации (вход по логину и паролю)
data class UserLogin(
    val identity: String,
    val password: String,
)

// 4. Вложенная коробочка для PocketBase. Сервер возвращает её внутри общего ответа авторизации
data class UserLoginResponseRecord (
    val id: String, // Уникальный ID пользователя на сервере
)

// 5. Главный ответ от сервера после успешного входа (То, что возвращает POST-метод "auth-with-password")
data class UserLoginResponse(
    val token: String, // Секретный ключ (сессия), по которому сервер дальше будет узнавать этого пользователя
    val record: UserLoginResponseRecord  // Вложенные данные о пользователе (его ID и т.д.)
)