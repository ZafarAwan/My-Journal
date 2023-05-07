package com.bbox.myjournal.repositories.local.feeling_repository

import com.bbox.myjournal.repositories.local.datasource.IJournalDataSource
import com.bbox.myjournal.repositories.local.factories.ILocalDataSourceFactory
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeelingRepository @Inject constructor(
    localDataSourceFactory: ILocalDataSourceFactory
) : IFeelingRepository {
    companion object {
        const val TAG = "FeelingRepository"
    }

    private val localDataSource by lazy {
        localDataSourceFactory.create(IJournalDataSource::class.java)
    }

    override suspend fun saveFeelingDetail(feelingDetail: FeelingDetail) = flow {
        emit(localDataSource.saveFeelingDetail(feelingDetail))
    }

    override suspend fun getFeelingDetailList() = flow {
        emit(localDataSource.getAllFeelings())
    }

}