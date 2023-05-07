package com.bbox.myjournal.repositories.local.factories

import com.bbox.myjournal.repositories.local.datasource.ILocalDataSource


interface ILocalDataSourceFactory {
    fun <T : ILocalDataSource> create(modelClass: Class<T>): T
}