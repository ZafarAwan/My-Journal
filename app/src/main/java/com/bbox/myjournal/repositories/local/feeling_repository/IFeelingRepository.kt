package com.bbox.myjournal.repositories.local.feeling_repository


import com.bbox.myjournal.repositories.local.factories.IRepository
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail
import kotlinx.coroutines.flow.Flow

interface IFeelingRepository : IRepository {

    suspend fun saveFeelingDetail(feelingDetail: FeelingDetail): Flow<Long>

    suspend fun getFeelingDetailList(): Flow<List<FeelingDetail>?>
}