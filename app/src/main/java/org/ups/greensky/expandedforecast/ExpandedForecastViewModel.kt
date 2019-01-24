package org.ups.greensky.expandedforecast

import org.ups.greensky.core.model.weather.component.precipitation.Precipitation

data class ExpandedForecastViewModel(val currentTime: String,
                                     val summary: String,
                                     val tempHigh: String,
                                     val tempLow: String,
                                     val hottestAt: String,
                                     val coldestAt: String,
                                     val precipitation: Precipitation)