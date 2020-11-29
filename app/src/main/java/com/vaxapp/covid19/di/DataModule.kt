package com.vaxapp.covid19.di

import android.content.Context
import com.vaxapp.covid19.data.ApiService
import com.vaxapp.covid19.data.CasesApiDataSource
import com.vaxapp.covid19.data.CasesDataBaseDataSource
import com.vaxapp.covid19.data.DataCasesRepository
import com.vaxapp.covid19.database.AppDatabase
import com.vaxapp.covid19.domain.CasesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val dataModule = module(definition = {
    single(definition = { provideRepository(get(), get()) })
    single(definition = { provideApiDataSource(get()) })
    single(definition = { provideDatabaseDataSource(get()) })
    single(definition = { provideApiService(androidContext()) })
    // single(definition = { provideDataBaseDao(androidContext()) })
})

/*fun provideDataBaseDao(androidContext: Context): DatabaseResponseDao {
    val database = AppDatabase.getInstance(androidContext)
    return database.databaseDao()
}*/

fun provideDatabaseDataSource(androidContext: Context): CasesDataBaseDataSource {
    val database = AppDatabase.getInstance(androidContext)
    return CasesDataBaseDataSource(database.databaseDao())
}

fun provideApiService(context: Context): ApiService {
    return ApiService.create(context)
}

fun provideApiDataSource(get: ApiService): CasesApiDataSource {
    return CasesApiDataSource(get)
}

fun provideRepository(
    dataSource: CasesApiDataSource,
    dataBaseDataSource: CasesDataBaseDataSource
): CasesRepository {
    return DataCasesRepository(dataSource, dataBaseDataSource)
}
