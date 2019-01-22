package org.ups.greensky.overview

import org.ups.greensky.common.BaseInputEvent

sealed class OverviewInputEvent : BaseInputEvent()

class ExpandWeatherForecastEvent(val time: Long) : OverviewInputEvent()