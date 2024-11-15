package com.example.mysteryShopper.data.model

import com.example.mysteryShopper.BuildConfig


object Constants {
    var BASE_URL = if (BuildConfig.URL.contains("http")) BuildConfig.URL else "https://${BuildConfig.URL}"

    const val ENCRYPT_KEY = "mywe@tedata$"
    const val PREFS_FILE  = "my_we"
}