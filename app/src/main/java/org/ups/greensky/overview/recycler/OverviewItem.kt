package org.ups.greensky.overview.recycler

sealed class OverviewItem(val time: Long)

class CurrentWeatherItem(
    val summary: String,
    val temperature: Int,
    time: Long
) : OverviewItem(time)

class DailyWeatherItem(
    val dayOfWeek: String,
    val lowTemperature: Int,
    val highTemperature: Int,
    time: Long
) : OverviewItem(time)