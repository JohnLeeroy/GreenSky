package org.ups.greensky.api.darksky

import io.reactivex.Observable
import org.ups.greensky.core.model.weather.component.BasicWeatherData
import retrofit2.http.GET
import retrofit2.http.Path


interface DarkSkyApi {

    @GET("/{latitude},{longitude},{unixTime}?exclude=minutely,hourly,daily,alerts,flags")
    fun getCurrentWeather(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("unixTime") unixTime: Long
    ): Observable<List<BasicWeatherData>>

    @GET("/{latitude},{longitude}?exclude=currently,minutely,hourly,alerts,flags")
    fun getCurrentWeeklyForecast(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Observable<List<BasicWeatherData>>

    @GET("/{latitude},{longitude},{unixTime}?exclude=currently,minutely,hourly,alerts,flags")
    fun getWeatherForecast(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("unixTime") unixTime: Long
    ): Observable<List<BasicWeatherData>>

}