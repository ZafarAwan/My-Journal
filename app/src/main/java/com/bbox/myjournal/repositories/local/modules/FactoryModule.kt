package com.bbox.myjournal.repositories.local.modules

import com.bbox.myjournal.repositories.local.factories.IJournalFactory
import com.bbox.myjournal.repositories.local.factories.ILocalDataSourceFactory
import com.bbox.myjournal.repositories.local.factories.JournalRepositoryFactory
import com.bbox.myjournal.repositories.local.factories.LocalDataSourceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FactoryModule {

    @Binds
    @Singleton
    fun bindJournalDataSourceFactory(factory: JournalRepositoryFactory): IJournalFactory


    @Binds
    @Singleton
    fun bindLocalDataSourceFactory(factory: LocalDataSourceFactory): ILocalDataSourceFactory


}