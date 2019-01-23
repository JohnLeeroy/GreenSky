package org.ups.greensky.overview

import io.reactivex.Observable
import org.ups.greensky.mvp.BaseView
import org.ups.greensky.overview.recycler.OverviewInputEvent
import org.ups.greensky.overview.recycler.OverviewItem

interface OverviewView : BaseView {

    fun setLocationLabel(text : String)

    fun onRefresh() : Observable<Unit>

    fun onItemClicked() : Observable<OverviewInputEvent>

    fun addOrUpdateOverviewAdapter(overviewItems: List<OverviewItem>)

    fun showError(message: String)
}