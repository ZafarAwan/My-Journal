package com.bbox.myjournal.repositories.local.db_models

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class DataConverters {

    private val TAG = "Converters"

    @TypeConverter
    fun feelingDetailToJson(value: FeelingDetail?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToFeelingDetailList(value: String?) =
        Gson().fromJson(value, Array<FeelingDetail>::class.java).toList()

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}