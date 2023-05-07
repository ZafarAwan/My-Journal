package com.bbox.myjournal.repositories.local.datasource

import com.bbox.myjournal.repositories.local.dao.JournalDao
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class JournalDataSource @Inject constructor(
    private val journalDao: JournalDao,
    private val dispatcher: CoroutineDispatcher
) : IJournalDataSource {

    companion object {
        const val TAG = "JournalDataSource"
    }

    override suspend fun saveFeelingDetail(detail: FeelingDetail): Long {
        return journalDao.insert(detail)
    }

    override suspend fun getAllFeelings(): List<FeelingDetail>? {
        return journalDao.getAllFeelingDetail()
    }
}