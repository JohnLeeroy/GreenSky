package org.ups.greensky.api.darksky

import io.reactivex.Observable
import org.ups.greensky.api.darksky.result.CurrentWeatherResult
import org.ups.greensky.api.darksky.result.UpcomingWeatherResult
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyService {

    @GET("{latitude},{longitude},{unixTime}?exclude=minutely,hourly,daily,alerts,flags")
    fun getCurrentWeather(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("unixTime") unixTime: Long
    ): Observable<CurrentWeatherResult>

    @GET("{latitude},{longitude}?exclude=currently,minutely,hourly,alerts,flags")
    fun getUpcomingWeeklyForecast(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Observable<UpcomingWeatherResult>

}