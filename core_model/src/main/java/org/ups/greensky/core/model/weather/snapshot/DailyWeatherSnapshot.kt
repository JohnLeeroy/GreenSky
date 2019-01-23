package org.ups.greensky.core.model.weather.snapshot

import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.TemperatureRange
import org.ups.greensky.core.model.weather.component.WeatherComponentContract

class DailyWeatherSnapshot(
    val tempRange: TemperatureRange,
    val apparentTempRange: TemperatureRange,
    time: Long,
    coordinate: Coordinate,
    summary: String,
    additionalWeatherData: Set<WeatherComponentContract>
) : BaseWeatherSnapshot(time, coordinate, summary, additionalWeatherData)