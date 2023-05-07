package com.bbox.myjournal.repositories.local.modules


import com.bbox.myjournal.repositories.local.datasource.IJournalDataSource
import com.bbox.myjournal.repositories.local.datasource.ILocalDataSource
import com.bbox.myjournal.repositories.local.datasource.JournalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @IntoMap
    @LocalDataSourceKey(IJournalDataSource::class)
    fun bindJournalLocalDataSource(localDataSource: JournalDataSource): ILocalDataSource

}