package org.ups.greensky.overview

import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.ups.greensky.interactor.GetCurrentWeather
import org.ups.greensky.interactor.GetCurrentWeeklyForecast
import org.ups.greensky.mvp.PresenterProvider

class OverviewPresenterFactory(kodein: Kodein) : PresenterProvider<OverviewView, OverviewPresenter>() {

    private val getCurrentWeather : GetCurrentWeather by kodein.instance()
    private val getCurrentWeeklyForecast : GetCurrentWeeklyForecast by kodein.instance()

    override fun provide(): OverviewPresenter {
        return OverviewPresenter(getCurrentWeather, getCurrentWeeklyForecast)
    }

}