package com.example.mysteryShopper.data.api

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("api/v1/settings/config")
    @Headers("No-Authentication: true")
    suspend fun getAppConfig(): Response<AppConfigEntity>

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): CharacterResponse

}