package org.ups.greensky.interactor

import io.reactivex.Observable
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot

class GetCurrentWeather(api: WeatherApiContract) : WeatherApiInteractor(api) {

    fun execute(coordinate: Coordinate, unixTime: Long): Observable<CurrentWeatherSnapshot> {
        return api.getCurrentWeather(coordinate, unixTime)
    }
}