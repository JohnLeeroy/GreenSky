package org.ups.greensky.interactor

import io.reactivex.Observable
import org.ups.greensky.core.model.weather.component.BasicWeatherData

class GetWeather() {

    fun execute() : Observable<BasicWeatherData> {
        return Observable.empty()
    }
}