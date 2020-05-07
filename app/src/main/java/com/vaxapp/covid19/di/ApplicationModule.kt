package com.vaxapp.covid19.di

import org.koin.dsl.module.Module
import org.koin.dsl.module.module

fun officeWeatherAppModules() = listOf(dataModule, domainModule, viewModule, module)

val module: Module = module(definition = {
})
