package org.ups.greensky.overview

import io.reactivex.Observable
import org.ups.greensky.mvp.BaseView
import org.ups.greensky.overview.recycler.OverviewInputEvent

interface OverviewView : BaseView {

    fun setLocationLabel(text : String)

    fun onRefresh() : Observable<Unit>

    fun onItemClicked() : Observable<OverviewInputEvent>
}