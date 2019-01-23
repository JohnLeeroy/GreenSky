package org.ups.greensky.overview.recycler

import org.ups.greensky.core.model.weather.component.precipitation.Precipitation
import org.ups.greensky.core.model.weather.component.precipitation.PrecipitationType

sealed class OverviewItem(val time: Long)

class CurrentWeatherItem(
    val summary: String,
    val temperature: String,
    val precipitation: Precipitation,
    time: Long
) : OverviewItem(time)

class DailyWeatherItem(
    val dayOfWeek: String,
    val lowTemperature: String,
    val highTemperature: String,
    val precipType: PrecipitationType,
    val precipChance: String,
    time: Long
) : OverviewItem(time)
