package com.example.mysteryShopper.data.repositories

import com.example.mysteryShopper.data.model.Response
import com.example.mysteryShopper.data.source.DataSource
import com.example.mysteryShopper.domain.entities.AppConfigEntity
import com.example.mysteryShopper.domain.repositories.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource): IRepository{
    override suspend fun getAppConfig(): Flow<Response<AppConfigEntity>> {
        return dataSource.getAppConfig()
    }
}
