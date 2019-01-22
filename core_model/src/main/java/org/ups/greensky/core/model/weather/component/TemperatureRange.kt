package org.ups.greensky.core.model.weather.component

data class TemperatureRange(
    val temperatureHigh: Float,
    val temperatureHighTime: Long,
    val temperatureLow: Float,
    val temperatureLowTime: Long
)