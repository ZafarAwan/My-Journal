package com.bbox.myjournal.repositories.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail

@Dao
interface JournalDao : BaseDao<FeelingDetail> {

    @Query("SELECT * FROM feelingDetail")
    suspend fun getAllFeelingDetail(): List<FeelingDetail>?
}