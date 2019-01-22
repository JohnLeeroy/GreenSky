package org.ups.greensky.core.model.weather.snapshot

import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.TemperatureRange
import org.ups.greensky.core.model.weather.component.WeatherComponentContract

class DailyWeatherSnapshot(
    time: Long,
    coordinate: Coordinate,
    summary: String,
    tempRange: TemperatureRange,
    apparentTempRange: TemperatureRange,
    additionalWeatherData: Set<WeatherComponentContract>
) : BaseWeatherSnapshot(time, coordinate, summary, additionalWeatherData)