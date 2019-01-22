package org.ups.greensky.core.model.weather.component.precipitation

class PrecipitationForecast(
    precipType: PrecipitationType,
    precipIntensity: Float,
    precipProbability: Float,
    val pecipIntensityMax: Float,
    val precipIntesityMaxTime: Long,
    val precipAccumulation: Float
) : Precipitation(precipType, precipIntensity, precipIntensity)