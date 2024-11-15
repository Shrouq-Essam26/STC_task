package com.example.mysteryShopper.core

import android.app.Application
import com.example.mysteryShopper.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
           Timber.plant(Timber.DebugTree())
    }
}