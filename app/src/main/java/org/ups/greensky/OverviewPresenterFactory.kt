package org.ups.greensky

import org.ups.greensky.mvp.PresenterProvider

class OverviewPresenterFactory : PresenterProvider<OverviewView, OverviewPresenter>() {

    override fun provide(): OverviewPresenter {
        return OverviewPresenter()
    }

}