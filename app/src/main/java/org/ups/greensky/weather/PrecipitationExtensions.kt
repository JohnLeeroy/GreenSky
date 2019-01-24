package org.ups.greensky.weather

import org.ups.greensky.core.model.weather.component.precipitation.Precipitation
import org.ups.greensky.core.model.weather.component.precipitation.PrecipitationType

//NOTE - JLI the implementation could be much more extensive
internal fun Precipitation.calculatePrecipitationType() : PrecipitationType {
    return if(precipProbability < .3f) {
        PrecipitationType.NONE
    } else {
        precipType
    }
}

internal fun Precipitation.getWeatherIcon() : Int {
    return calculatePrecipitationType().getIcon()
}

internal fun PrecipitationType.getIcon() : Int {
    return when(this) {
        PrecipitationType.RAIN -> PrecipitationIcon.RAIN.drawableRes
        PrecipitationType.SNOW -> PrecipitationIcon.SNOW.drawableRes
        PrecipitationType.SLEET -> PrecipitationIcon.SLEET.drawableRes
        PrecipitationType.NONE -> PrecipitationIcon.NONE.drawableRes
    }
}