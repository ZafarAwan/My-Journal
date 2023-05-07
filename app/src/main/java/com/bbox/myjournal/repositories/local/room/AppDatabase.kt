package com.bbox.myjournal.repositories.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bbox.myjournal.repositories.local.dao.JournalDao
import com.bbox.myjournal.repositories.local.db_models.DataConverters
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail

@Database(
    entities = [
        FeelingDetail::class
    ],
    version = 1, exportSchema = false
)

@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun journalDao(): JournalDao
}