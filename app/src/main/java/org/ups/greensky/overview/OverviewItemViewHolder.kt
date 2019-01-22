package org.ups.greensky.overview

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_current_weather_item.view.*
import kotlinx.android.synthetic.main.layout_daily_weather_item.view.*
import org.ups.greensky.common.BaseViewHolder

class OverviewItemViewHolder(view: View) : BaseViewHolder<OverviewItem, OverviewInputEvent>(view) {

    override fun bind(model: OverviewItem) {
        super.bind(model)
        when (model) {
            is DailyWeatherItem -> {
                itemView.tempHighLabel.text = model.highTemperature.toString()
                itemView.tempLowLabel.text = model.lowTemperature.toString()
                itemView.dayOfWeekLabel.text = model.dayOfWeek
            }
            is CurrentWeatherItem -> {
                itemView.tempLabel.text = model.temperature.toString()
                itemView.weatherLabel.text = model.summary
            }
        }
    }

    override fun getInputObservable(): Observable<OverviewInputEvent> {
        return itemView.clicks()
            .map { _ ->
                model?.time?.let { time -> ExpandWeatherForecastEvent(time) }
            }
    }
}