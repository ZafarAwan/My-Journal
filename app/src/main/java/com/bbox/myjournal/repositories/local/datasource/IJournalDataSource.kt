package com.bbox.myjournal.repositories.local.datasource

import com.bbox.myjournal.repositories.local.db_models.FeelingDetail

interface IJournalDataSource : ILocalDataSource {

    suspend fun saveFeelingDetail(detail: FeelingDetail):Long

    suspend fun getAllFeelings(): List<FeelingDetail>?

}