package com.example.mysteryShopper.domain.usecases

import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import com.example.mysteryShopper.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppConfigUseCase @Inject constructor(private val repository: IRepository) {
    suspend fun build(): Flow<Response<AppConfigEntity>> {
        return repository.getAppConfig()
    }
}