package com.example.mysteryShopper.data.source

import com.example.mysteryShopper.data.api.Api
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataSource @Inject constructor(private val api: Api, private val prefsHelper: PreferencesHelper) {
    suspend fun getAppConfig(): Flow<Response<AppConfigEntity>> {
        return flow { emit(api.getAppConfig())}
    }
}