package org.ups.greensky.api.darksky

import org.ups.greensky.api.darksky.result.CurrentWeatherResult
import org.ups.greensky.api.darksky.result.Currently
import org.ups.greensky.api.darksky.result.DailyItem
import org.ups.greensky.api.darksky.result.UpcomingWeatherResult
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.BasicWeatherData
import org.ups.greensky.core.model.weather.component.TemperatureRange
import org.ups.greensky.core.model.weather.component.precipitation.Precipitation
import org.ups.greensky.core.model.weather.component.precipitation.PrecipitationType
import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot

internal fun CurrentWeatherResult.mapToCurrentWeatherSnapshot(): CurrentWeatherSnapshot {
    return CurrentWeatherSnapshot(
        currently.mapToBasicWeatherData(),
        System.currentTimeMillis() / 1000,
        Coordinate(latitude, longitude),
        currently.summary, emptySet()
    )
}

internal fun UpcomingWeatherResult.mapToDailyWeatherSnapshot(coordinate: Coordinate): DailyWeatherSnapshot {
    val dailyItem = daily.data.first()
    return dailyItem.mapToDailyWeatherSnapshot(coordinate)
}

internal fun UpcomingWeatherResult.mapToDailyWeatherSnapshotList(coordinate: Coordinate): List<DailyWeatherSnapshot> {
    val result = mutableListOf<DailyWeatherSnapshot>()
    daily.data.forEach {
        result.add(it.mapToDailyWeatherSnapshot(coordinate))
    }
    return result
}

private fun Currently.getPrecipitation(): Precipitation {
    // NOTE-JLI, precipType can be null if precipIntensity and precipProbability is 0
    val precipType =
        if (precipType != null)
            precipType.toPrecipitationType()
        else
            PrecipitationType.RAIN

    return Precipitation(
        precipType,
        precipIntensity.toFloat(),
        precipProbability.toFloat()
    )
}

private fun Currently.mapToBasicWeatherData(): BasicWeatherData {
    return BasicWeatherData(
        temperature.toFloat(),
        humidity.toFloat(),
        getPrecipitation()
    )
}

private fun DailyItem.mapToDailyWeatherSnapshot(coordinate: Coordinate): DailyWeatherSnapshot {
    return DailyWeatherSnapshot(
        getTemperatureRange(),
        getApparentTemperatureRange(),
        getPrecipitation(),
        time,
        coordinate,
        summary,
        emptySet()
    )
}


private fun DailyItem.getPrecipitation(): Precipitation {
    // NOTE-JLI, precipType can be null if precipIntensity and precipProbability is 0
    val precipType =
        if (precipType != null)
            precipType.toPrecipitationType()
        else
            PrecipitationType.RAIN

    return Precipitation(
        precipType,
        precipIntensity.toFloat(),
        precipProbability.toFloat()
    )
}

private fun DailyItem.getTemperatureRange(): TemperatureRange {
    return TemperatureRange(
        temperatureHigh.toFloat(),
        temperatureHighTime,
        temperatureLow.toFloat(),
        temperatureLowTime
    )
}

private fun DailyItem.getApparentTemperatureRange(): TemperatureRange {
    return TemperatureRange(
        apparentTemperatureHigh.toFloat(),
        apparentTemperatureHighTime,
        apparentTemperatureLow.toFloat(),
        apparentTemperatureLowTime
    )
}

private fun String?.toPrecipitationType(): PrecipitationType {
    return when (this) {
        "rain" -> PrecipitationType.RAIN
        "sleet" -> PrecipitationType.SLEET
        "snow" -> PrecipitationType.SNOW
        else -> {
            throw IllegalArgumentException("Unknown precipitation type")
        }
    }
}