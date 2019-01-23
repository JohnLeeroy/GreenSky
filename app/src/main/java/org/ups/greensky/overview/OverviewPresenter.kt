package org.ups.greensky.overview

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.ups.greensky.api.ApiConfig
import org.ups.greensky.api.darksky.DarkSkyApiFactory
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.interactor.GetCurrentWeather
import org.ups.greensky.interactor.GetCurrentWeeklyForecast
import org.ups.greensky.mvp.BasePresenter
import timber.log.Timber

class OverviewPresenter(
    private val getCurrentWeather: GetCurrentWeather,
    private val getCurrentWeeklyForecast: GetCurrentWeeklyForecast
) : BasePresenter<OverviewView>() {

    override fun onAttach() {
        getCurrentWeather.execute(Coordinate(37.8267,-122.4233), System.currentTimeMillis()/1000)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString())
            }, {
                Timber.e(it)
            })

        getCurrentWeeklyForecast.execute(Coordinate(37.8267,-122.4233))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d(it.toString())
            }, {
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