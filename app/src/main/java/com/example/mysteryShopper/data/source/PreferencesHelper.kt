package com.example.mysteryShopper.data.source

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesHelper @Inject constructor(
    private val mSharedPreferences: SharedPreferences
) {

    init {
        instance = this
    }

    companion object {
        @get:Synchronized
        lateinit var instance: PreferencesHelper
    }


    private val prefAccessToken = "pref.token"
    var accessToken: String?
        get() = mSharedPreferences.getString(prefAccessToken, "")
        set(value) = mSharedPreferences.edit().putString(prefAccessToken, value).apply()

    private val prefMinVersion = "pref.minVersion"
    var minVersion: Int
        get() = mSharedPreferences.getInt(prefMinVersion, 1)
        set(value) = mSharedPreferences.edit().putInt(prefMinVersion, value).apply()

    private val prefUserName = "pref.userName"
    var userName: String
        get() = mSharedPreferences.getString(prefUserName, "")!!
        set(value) = mSharedPreferences.edit().putString(prefUserName, value).apply()

    private val prefPassword = "pref.password"
    var password: String
        get() = mSharedPreferences.getString(prefPassword, "")!!
        set(value) = mSharedPreferences.edit().putString(prefPassword, value).apply()


    fun clearSharedPref() {
        mSharedPreferences.edit().clear().apply()
    }
}