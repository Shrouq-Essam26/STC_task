package com.example.mysteryShopper.data.source

import com.example.mysteryShopper.data.api.Api
import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.data.model.SectionResponse
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: Api,
    private val prefsHelper: PreferencesHelper
) {


    suspend fun getCharacters(limit: Int, offset: Int): Flow<CharacterResponse> {
        return flow {
            emit(api.getCharacters(limit, offset))
        }
    }

    suspend fun fetchSectionDetails(resourceURI: String): Flow<List<SectionModel>> {
        return flow {
            val response = api.getResourceDetails(resourceURI)
            if (response.isSuccessful) {
                val results = response.body()?.data?.results.orEmpty()
                emit(
                    results.map {
                        SectionModel(
                            id = it.id,
                            title = it.title,
                            description = it.description ?: "No description available.",
                            thumbnailUrl = it.thumbnail?.let { thumb -> "${thumb.path}.${thumb.extension}" }
                                ?: ""
                        )
                    }
                )
            } else {
                throw Exception(
                    "Failed to fetch section details: ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }
    }
}