package com.bbox.myjournal.repositories.local.modules

import com.bbox.myjournal.repositories.local.factories.IRepository
import com.bbox.myjournal.repositories.local.feeling_repository.FeelingRepository
import com.bbox.myjournal.repositories.local.feeling_repository.IFeelingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @IntoMap
    @RepositoryKey(IFeelingRepository::class)
    fun bindFeelingRepository(repository: FeelingRepository): IRepository

}