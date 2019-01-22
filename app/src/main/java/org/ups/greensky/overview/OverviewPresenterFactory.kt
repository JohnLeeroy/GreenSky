package org.ups.greensky.overview

import org.ups.greensky.mvp.PresenterProvider

class OverviewPresenterFactory : PresenterProvider<OverviewView, OverviewPresenter>() {

    override fun provide(): OverviewPresenter {
        return OverviewPresenter()
    }

}