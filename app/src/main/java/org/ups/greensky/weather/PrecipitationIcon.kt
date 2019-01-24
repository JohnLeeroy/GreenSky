package org.ups.greensky.weather

import org.ups.greensky.R

internal enum class PrecipitationIcon(val drawableRes: Int) {
    RAIN(R.drawable.ic_cloud_rain_alt),
    SNOW(R.drawable.ic_cloud_snow_alt),
    SLEET(R.drawable.ic_cloud_hail_alt),
    NONE(R.drawable.ic_sun),
}
