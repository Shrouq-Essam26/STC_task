package com.example.mysteryShopper.presentation.di

import android.app.Application
import android.content.SharedPreferences
import com.example.mysteryShopper.data.api.Api
import com.example.mysteryShopper.data.model.Constants.ENCRYPT_KEY
import com.example.mysteryShopper.data.model.Constants.PREFS_FILE
import com.example.mysteryShopper.data.source.DataSource
import com.example.mysteryShopper.data.source.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return com.securepreferences.SecurePreferences(application, ENCRYPT_KEY, PREFS_FILE)
    }

    @Provides
    @Singleton
    fun providePrefHelper(sharedPref: SharedPreferences): PreferencesHelper {
        return PreferencesHelper(mSharedPreferences = sharedPref)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: Api, prefsHelper: PreferencesHelper): DataSource = DataSource(api = api, prefsHelper = prefsHelper)
}