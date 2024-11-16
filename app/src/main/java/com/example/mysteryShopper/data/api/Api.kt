package com.example.mysteryShopper.data.api

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.SectionResponse
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {


    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): CharacterResponse


    @GET
    suspend fun getResourceDetails(@Url resourceURI: String): Response<SectionResponse>
}