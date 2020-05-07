package com.vaxapp.covid19

import android.app.Application
import com.vaxapp.covid19.di.officeWeatherAppModules
import org.koin.android.ext.android.startKoin

//TODO: rename
class OfficeWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, officeWeatherAppModules())
    }
}