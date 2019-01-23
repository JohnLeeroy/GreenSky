package org.ups.greensky.interactor.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.ups.greensky.interactor.GetCurrentWeather
import org.ups.greensky.interactor.GetCurrentWeeklyForecast

class DomainModuleFactory {
    fun make(): Kodein.Module {
        return Kodein.Module(name = "Domain") {
            bind<GetCurrentWeather>() with provider { GetCurrentWeather(instance()) }
            bind<GetCurrentWeeklyForecast>() with provider { GetCurrentWeeklyForecast(instance()) }
        }
    }

}