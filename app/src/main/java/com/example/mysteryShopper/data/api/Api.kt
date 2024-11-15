package com.example.mysteryShopper.data.api

import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @GET("api/v1/settings/config")
    @Headers("No-Authentication: true")
    suspend fun getAppConfig(): Response<AppConfigEntity>
}