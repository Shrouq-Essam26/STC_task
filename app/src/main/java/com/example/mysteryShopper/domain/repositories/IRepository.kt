package com.example.mysteryShopper.domain.repositories

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun getAppConfig(): Flow<Response<AppConfigEntity>>
    suspend fun getCharacters(limit:Int , offset:Int): Flow<CharacterResponse>
}
