package com.keyneez.data.source

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPrefDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun setUserName(name: String) {
        prefs.edit().putString(PREF_USER_NAME, name).apply()
    }

    fun getUserName(): String? {
        return prefs.getString(PREF_USER_NAME, "")
    }

    fun setAccessToken(accessToken: String) {
        prefs.edit().putString(PREF_ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString(PREF_ACCESS_TOKEN, "")
    }

    companion object {
        const val PREF_USER_NAME = "userName"
        const val PREF_ACCESS_TOKEN = "accessToken"
    }
}
