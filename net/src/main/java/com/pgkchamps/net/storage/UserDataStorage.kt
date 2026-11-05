package com.pgkchamps.net.storage

interface UserDataStorage {

    fun saveToken(token: String)
    fun getToken(): String

}