package org.ups.greensky.overview

import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot
import org.ups.greensky.overview.recycler.CurrentWeatherItem
import org.ups.greensky.overview.recycler.DailyWeatherItem
import java.text.SimpleDateFormat

internal fun CurrentWeatherSnapshot.mapToWeatherItem(): CurrentWeatherItem {
    return CurrentWeatherItem(
        summary,
        basicWeatherData.temperature.toString(),
        basicWeatherData.precipitation,
        time
    )
}

internal fun DailyWeatherSnapshot.mapToWeatherItem(): DailyWeatherItem {
    val sdf = SimpleDateFormat("DD EEE")
    val dateFormat = java.util.Date(time * 1000)
    return DailyWeatherItem(
        sdf.format(dateFormat),
        String.format("%.0f°", tempRange.temperatureLow),
        String.format("%.0f°", tempRange.temperatureHigh),
        precipitation.precipType,
        String.format("%d%%", (precipitation.precipProbability * 100).toInt()),
        time
    )
}