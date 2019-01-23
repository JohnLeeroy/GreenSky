package org.ups.greensky.overview.recycler

sealed class OverviewItem(val time: Long)

class CurrentWeatherItem(
    val summary: String,
    val temperature: String,
    time: Long
) : OverviewItem(time)

class DailyWeatherItem(
    val dayOfWeek: String,
    val lowTemperature: String,
    val highTemperature: String,
    val precipChance: String,
    time: Long
) : OverviewItem(time)
