package com.example.mysteryShopper.domain.usecases

import com.example.mysteryShopper.data.model.CharacterResponse
import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import com.example.mysteryShopper.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: IRepository) {
    suspend fun build(limit: Int , offset: Int): Flow<CharacterResponse> {
        return repository.getCharacters(limit , offset)
    }
}