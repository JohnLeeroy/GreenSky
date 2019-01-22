package org.ups.greensky.api

import io.reactivex.Observable
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.BaseWeatherSnapshot

interface WeatherApiContract {

    fun getCurrentWeather(coordinate: Coordinate, unixTime: Long): Observable<BaseWeatherSnapshot>

    fun getCurrentWeeklyForecast(coordinate: Coordinate, unixTime: Long): Observable<List<BaseWeatherSnapshot>>

    fun getWeatherForecast(coordinate: Coordinate, unixTime: Long): Observable<BaseWeatherSnapshot>

}