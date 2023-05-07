package com.bbox.myjournal.repositories.local.factories


import javax.inject.Inject
import javax.inject.Provider

class JournalRepositoryFactory @Inject constructor(
    private val creators: Map<Class<out IRepository>, @JvmSuppressWildcards Provider<IRepository>>
) : IJournalFactory {
    override fun <T : IRepository> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown Repository Class: $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}