package org.ups.greensky.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import org.ups.greensky.R
import org.ups.greensky.common.BaseAdapter
import org.ups.greensky.common.WrappedItem
import java.lang.IllegalStateException

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
            else -> throw IllegalStateException("Unknown item type")
        }
        return OverviewItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> WEATHER_HEADER
            else -> DAILY_WEATHER
        }
        return super.getItemViewType(position)
    }

    override fun addOrUpdateItems(items: List<WrappedItem<OverviewItem>>) {
        data.addAll(items)
    }
}