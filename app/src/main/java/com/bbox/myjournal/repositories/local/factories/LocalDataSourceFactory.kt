package com.bbox.myjournal.repositories.local.factories

import com.bbox.myjournal.repositories.local.datasource.ILocalDataSource
import javax.inject.Inject
import javax.inject.Provider

class LocalDataSourceFactory @Inject constructor(
        private val creators: Map<Class<out ILocalDataSource>, @JvmSuppressWildcards Provider<ILocalDataSource>>
) : ILocalDataSourceFactory {
    override fun <T : ILocalDataSource> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
                ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
                ?: throw IllegalArgumentException("Unknown LocalDataSource Class: $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}