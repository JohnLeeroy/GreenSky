package org.ups.greensky.overview.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import org.ups.greensky.R
import org.ups.greensky.common.BaseAdapter

class OverviewAdapter : BaseAdapter<OverviewItem, OverviewInputEvent, OverviewItemViewHolder>() {

    companion object {
        private const val WEATHER_HEADER = 1
        private const val DAILY_WEATHER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = when(viewType) {
            WEATHER_HEADER -> layoutInflater.inflate(R.layout.layout_current_weather_item, parent, false)
            DAILY_WEATHER -> layoutInflater.inflate(R.layout.layout_daily_weather_item, parent, false)
            else -> {
                throw IllegalArgumentException("Unknown OverviewAdapter view type")
            }
        }
        val viewHolder = OverviewItemViewHolder(view)
        observeViewHolderInput(viewHolder.getInputObservable())
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
            is CurrentWeatherItem -> WEATHER_HEADER
            is DailyWeatherItem -> DAILY_WEATHER
        }
    }

    override fun addOrUpdateItems(items: List<OverviewItem>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}