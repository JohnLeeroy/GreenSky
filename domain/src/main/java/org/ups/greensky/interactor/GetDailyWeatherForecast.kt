package org.ups.greensky.interactor

import io.reactivex.Observable
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot

class GetDailyWeatherForecast(api: WeatherApiContract) : WeatherApiInteractor(api) {

    fun execute(coordinate: Coordinate, unixTime: Long): Observable<DailyWeatherSnapshot> {
        return api.getWeatherForecast(coordinate, unixTime)
    }
}