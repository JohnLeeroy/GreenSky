package org.ups.greensky.core.model.weather.snapshot

import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.BasicWeatherData
import org.ups.greensky.core.model.weather.component.WeatherComponentContract

abstract class BaseWeatherSnapshot(
    val time: Long,
    val coordinate: Coordinate,
    val summary: String,
    val additionalWeatherData: Set<WeatherComponentContract>
)