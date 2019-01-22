package org.ups.greensky.core.model.weather.snapshot

import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.BasicWeatherData
import org.ups.greensky.core.model.weather.component.WeatherComponentContract

class CurrentWeatherSnapshot(
    time: Long,
    coordinate: Coordinate,
    summary: String,
    basicWeatherData: BasicWeatherData,
    additionalWeatherData: Set<WeatherComponentContract>
) : BaseWeatherSnapshot(time, coordinate, summary, additionalWeatherData)