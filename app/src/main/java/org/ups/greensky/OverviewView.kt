package org.ups.greensky

import io.reactivex.Observable
import org.ups.greensky.mvp.BaseView

interface OverviewView : BaseView {

    fun onRefresh() : Observable<Unit>

    fun onItemClicked() : Observable<OverviewInputEvent>
}