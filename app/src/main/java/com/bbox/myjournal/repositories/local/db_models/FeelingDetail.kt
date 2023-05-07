package com.bbox.myjournal.repositories.local.db_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class FeelingDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var feelingData: String,
    var feelingColor: String,
    var feelingDateAndTime: Date?,
)
