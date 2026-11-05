package com.pgkchamps.net.storage

import android.content.Context
import androidx.core.content.edit
import com.pgkchamps.net.common.ContextHolder

class UserDataStrorageImpl: UserDataStorage {

    val pref = ContextHolder.getContext().applicationContext.getSharedPreferences("user",Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        pref.edit {
            putString("token",token)
        }
    }

    override fun getToken(): String {
        return pref.getString("token",null) ?: ""
    }
}