package com.example.mysteryShopper.presentation.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.mysteryShopper.data.room.OfflineStatusDao
import com.example.mysteryShopper.data.room.OfflineStatusDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): OfflineStatusDataBase {
        return Room
            .databaseBuilder(application, OfflineStatusDataBase::class.java, "OfflineStatus.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideOfflineStatusDao(@NonNull database: OfflineStatusDataBase): OfflineStatusDao {
        return database.offlineStatusDao()
    }



}