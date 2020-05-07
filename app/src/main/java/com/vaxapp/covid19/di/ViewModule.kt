package com.vaxapp.covid19.di

import com.vaxapp.covid19.view.main.MainPresenter
import com.vaxapp.covid19.view.main.ViewResponseMapper
import org.koin.dsl.module.module

val viewModule = module(definition = {
    single(definition = { MainPresenter(get(), provideGetViewResponseMapper()) })
})

fun provideGetViewResponseMapper(): ViewResponseMapper {
    return ViewResponseMapper()
}
