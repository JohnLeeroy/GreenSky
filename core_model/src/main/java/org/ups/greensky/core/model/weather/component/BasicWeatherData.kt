package org.ups.greensky.core.model.weather.component

import org.ups.greensky.core.model.weather.component.precipitation.Precipitation

data class BasicWeatherData(
    val temperature: Float,
    val humidity: Float,
    val precipitation: Precipitation
) : WeatherComponentContract