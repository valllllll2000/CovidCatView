package com.vaxapp.covid19.di

import android.content.Context
import com.vaxapp.covid19.data.ApiService
import com.vaxapp.covid19.data.CasesApiDataSource
import com.vaxapp.covid19.data.DataCasesRepository
import com.vaxapp.covid19.domain.CasesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val dataModule = module(definition = {
    single(definition = { provideOfficeWeatherRepository(get()) })
    single(definition = { provideOfficeWeatherDataSource(get()) })
    single(definition = { provideApiService(androidContext()) })
})

fun provideApiService(context: Context): ApiService {
    return ApiService.create(context)
}

fun provideOfficeWeatherDataSource(get: ApiService): CasesApiDataSource {
    return CasesApiDataSource(get)
}

fun provideOfficeWeatherRepository(dataSource: CasesApiDataSource): CasesRepository {
    return DataCasesRepository(dataSource)
}
