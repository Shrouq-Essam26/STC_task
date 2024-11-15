package com.example.mysteryShopper.presentation.di

import com.example.mysteryShopper.data.repositories.RepositoryImpl
import com.example.mysteryShopper.domain.repositories.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {
    companion object {
//        @Provides
//        fun providesApiService(retrofit: Retrofit) = retrofit.create(Api::class.java)
    }
    @Binds
    internal abstract fun bindsRepository(repositoryImpl: RepositoryImpl): IRepository
}