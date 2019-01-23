package org.ups.greensky.api.darksky

import io.reactivex.Observable
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot

class DarkSkyApi(private val darkSkyService: DarkSkyService) : WeatherApiContract {

    override fun getCurrentWeather(coordinate: Coordinate, unixTime: Long): Observable<CurrentWeatherSnapshot> {
        return darkSkyService.getCurrentWeather(coordinate.latitude, coordinate.longitude, unixTime)
            .map { it.mapToCurrentWeatherSnapshot() }
    }

    override fun getCurrentWeeklyForecast(
        coordinate: Coordinate): Observable<List<DailyWeatherSnapshot>> {
        return darkSkyService.getUpcomingWeeklyForecast(coordinate.latitude, coordinate.longitude)
            .map { it.mapToDailyWeatherSnapshotList(coordinate) }
    }

}