package org.ups.greensky.core.model.weather.component.precipitation

import org.ups.greensky.core.model.weather.component.WeatherComponentContract

open class Precipitation(
    val precipType: PrecipitationType,
    val precipIntensity: Float,
    val precipProbability: Float
) : WeatherComponentContract
