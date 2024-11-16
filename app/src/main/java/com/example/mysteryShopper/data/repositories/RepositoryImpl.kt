package com.example.mysteryShopper.data.repositories

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.data.source.DataSource
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import com.example.mysteryShopper.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource) : IRepository {


    override suspend fun getCharacters(limit: Int, offset: Int): Flow<CharacterResponse> {
        return dataSource.getCharacters(limit, offset)
    }

    override suspend fun fetchSectionDetails(resourceURI: String): Flow<List<SectionModel>> {
      return  dataSource.fetchSectionDetails(resourceURI)
    }
}
