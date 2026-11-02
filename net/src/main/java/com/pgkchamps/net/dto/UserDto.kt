package com.pgkchamps.net.dto

data class RegDto(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val name: String,
)

data class UserProfile(
    val email: String,
    val name: String,
)

data class UserLogin(
    val identity: String,
    val password: String,
)


data class UserLoginResponseRecord (
    val id: String,
)

data class UserLoginResponse(
    val token: String,
    val record: UserLoginResponseRecord
)