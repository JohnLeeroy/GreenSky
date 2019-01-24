package org.ups.greensky.api

import io.reactivex.Observable
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.BaseWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot

interface WeatherApiContract {

    fun getCurrentWeather(coordinate: Coordinate, unixTime: Long): Observable<CurrentWeatherSnapshot>

    fun getWeatherForecast(coordinate: Coordinate, unixTime: Long): Observable<DailyWeatherSnapshot>

    fun getCurrentWeeklyForecast(coordinate: Coordinate): Observable<List<DailyWeatherSnapshot>>

}