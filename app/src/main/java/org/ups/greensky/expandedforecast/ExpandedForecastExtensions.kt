package org.ups.greensky.expandedforecast

import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot
import java.text.SimpleDateFormat
import java.util.*

fun DailyWeatherSnapshot.toExpandedForecastViewModel(): ExpandedForecastViewModel {
    val hourMinuteFormat = SimpleDateFormat("h:mm a")
    val headerFormat = SimpleDateFormat("MMMM DD, EEEE")
    val header = headerFormat.format(Date(time * 1000))
    val hottestAt = hourMinuteFormat.format(Date(tempRange.temperatureHighTime * 1000))
    val coldestAt = hourMinuteFormat.format(Date(tempRange.temperatureLowTime * 1000))
    return ExpandedForecastViewModel(
        header,
        summary,
        String.format("%.0f°", tempRange.temperatureHigh),
        String.format("%.0f°", tempRange.temperatureLow),
        hottestAt,
        coldestAt,
        precipitation
    )
}