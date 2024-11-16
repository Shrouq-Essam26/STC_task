package com.example.mysteryShopper.domain.usecases

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSectionDetailsUseCase @Inject constructor(private val repository: IRepository) {
    suspend fun build(resourceURI: String): Flow<List<SectionModel>> {
        return repository.fetchSectionDetails(resourceURI)
    }
}