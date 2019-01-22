package org.ups.greensky.overview

import io.reactivex.Observable
import org.ups.greensky.mvp.BaseView

interface OverviewView : BaseView {

    fun onRefresh() : Observable<Unit>

    fun onItemClicked() : Observable<OverviewInputEvent>
}