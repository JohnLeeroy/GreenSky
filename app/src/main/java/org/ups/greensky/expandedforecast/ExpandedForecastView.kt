package org.ups.greensky.expandedforecast

import io.reactivex.Observable
import org.ups.greensky.mvp.BaseView

interface ExpandedForecastView : BaseView {

    fun updateUi(viewModel: ExpandedForecastViewModel)

    fun backButtonInputObservable() : Observable<Unit>

    fun closeView()
}