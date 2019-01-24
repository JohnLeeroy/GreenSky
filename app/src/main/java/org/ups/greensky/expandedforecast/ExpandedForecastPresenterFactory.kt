package org.ups.greensky.expandedforecast

import android.os.Bundle
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.interactor.GetDailyWeatherForecast
import org.ups.greensky.mvp.PresenterProvider

class ExpandedForecastPresenterFactory(kodein: Kodein, private val bundle: Bundle) :
    PresenterProvider<ExpandedForecastView, ExpandedForecastPresenter>() {

    private val getDailyWeatherForecast: GetDailyWeatherForecast by kodein.instance()

    override fun provide(): ExpandedForecastPresenter {
        val latitude = bundle.getDouble(ExpandedForecastActivity.LATITUDE_ARG)
        val longitude = bundle.getDouble(ExpandedForecastActivity.LONGITUDE_ARG)
        val unixTime = bundle.getLong(ExpandedForecastActivity.UNIX_TIME_ARG)
        return ExpandedForecastPresenter(
            Coordinate(latitude, longitude),
            unixTime,
            getDailyWeatherForecast
        )
    }
}