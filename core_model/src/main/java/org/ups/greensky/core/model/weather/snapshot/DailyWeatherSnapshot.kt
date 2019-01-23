package org.ups.greensky.core.model.weather.snapshot

import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.TemperatureRange
import org.ups.greensky.core.model.weather.component.WeatherComponentContract
import org.ups.greensky.core.model.weather.component.precipitation.Precipitation

class DailyWeatherSnapshot(
    val tempRange: TemperatureRange,
    val apparentTempRange: TemperatureRange,
    val precipitation: Precipitation,
    time: Long,
    coordinate: Coordinate,
    summary: String,
    additionalWeatherData: Set<WeatherComponentContract>
) : BaseWeatherSnapshot(time, coordinate, summary, additionalWeatherData)