package org.ups.greensky.weather

import org.ups.greensky.R

enum class PrecipitationIcon(val drawableRes: Int) {
    RAIN(R.drawable.ic_cloud_rain_alt),
    SNOW(R.drawable.ic_cloud_snow_alt),
    SLEET(R.drawable.ic_cloud_hail_alt),
}