package com.bbox.myjournal.repositories.local.modules

import com.bbox.myjournal.repositories.local.datasource.ILocalDataSource
import dagger.MapKey
import javax.inject.Singleton
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@Singleton
annotation class LocalDataSourceKey(val value: KClass<out ILocalDataSource>)