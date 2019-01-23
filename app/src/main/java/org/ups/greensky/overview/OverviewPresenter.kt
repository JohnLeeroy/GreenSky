package org.ups.greensky.overview

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.interactor.GetCurrentWeather
import org.ups.greensky.interactor.GetCurrentWeeklyForecast
import org.ups.greensky.mvp.BasePresenter
import org.ups.greensky.overview.recycler.OverviewItem
import timber.log.Timber

class OverviewPresenter(
    private val getCurrentWeather: GetCurrentWeather,
    private val getCurrentWeeklyForecast: GetCurrentWeeklyForecast
) : BasePresenter<OverviewView>() {

    override fun onAttach() {
        getCurrentWeather.execute(Coordinate(47.608013, -122.335167), System.currentTimeMillis() / 1000)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString())
                view?.addOrUpdateOverviewAdapter(
                    listOf<OverviewItem>(it.mapToWeatherItem())
                )
            }, {
                it.message?.let { errorMessage -> view?.showError(errorMessage) }
                Timber.e(it)
            })

        getCurrentWeeklyForecast.execute(Coordinate(47.608013, -122.335167))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val list = mutableListOf<OverviewItem>()
                it.forEach {
                    list.add(it.mapToWeatherItem())
                }
                view?.addOrUpdateOverviewAdapter(list)
            }, {
                it.message?.let { errorMessage -> view?.showError(errorMessage) }
                Timber.e(it)
            })
    }

    override fun onDetach() {

    }

    private fun observeRefresh() {
        view?.let {
            it.onRefresh()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { })
        }
    }

}