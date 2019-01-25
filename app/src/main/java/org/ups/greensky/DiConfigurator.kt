package org.ups.greensky

import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.ups.greensky.api.ApiConfig
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.api.darksky.DarkSkyApiFactory
import org.ups.greensky.common.rx.GSSchedulers
import org.ups.greensky.common.rx.SchedulerImpl
import org.ups.greensky.interactor.di.DomainModuleFactory

class DiConfigurator {

    fun configureDi(applicationContext: Context, apiConfig: ApiConfig) : Kodein {
        return Kodein.lazy {
            bind<Context>() with instance(applicationContext)
            bind<ApiConfig>() with instance(apiConfig)
            bind<WeatherApiContract>() with singleton { DarkSkyApiFactory().make(apiConfig) }
            bind<GSSchedulers>() with singleton { SchedulerImpl() }
            importOnce(DomainModuleFactory().make())
        }
    }
}