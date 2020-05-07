package com.vaxapp.covid19.di

import com.vaxapp.covid19.domain.CasesRepository
import com.vaxapp.covid19.domain.GetCasesUseCase
import org.koin.dsl.module.module

val domainModule = module(definition = {
    single(definition = { provideGetOfficeUseCase(get()) })
})

fun provideGetOfficeUseCase(repository: CasesRepository): GetCasesUseCase {
    return GetCasesUseCase(repository)
}
