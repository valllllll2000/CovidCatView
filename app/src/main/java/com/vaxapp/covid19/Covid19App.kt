package com.vaxapp.covid19

import android.app.Application
import com.vaxapp.covid19.di.officeWeatherAppModules
import org.koin.android.ext.android.startKoin

class Covid19App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, officeWeatherAppModules())
    }
}
