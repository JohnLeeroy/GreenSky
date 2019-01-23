package org.ups.greensky.interactor

import io.reactivex.Observable
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot

class GetCurrentWeeklyForecast(api: WeatherApiContract) : WeatherApiInteractor(api) {

    fun execute(coordinate: Coordinate): Observable<List<DailyWeatherSnapshot>> {
        return api.getCurrentWeeklyForecast(coordinate)
    }
}