package org.ups.greensky.expandedforecast

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.interactor.GetDailyWeatherForecast
import org.ups.greensky.mvp.BasePresenter
import timber.log.Timber

class ExpandedForecastPresenter(
    val coordinate: Coordinate,
    val unixTime: Long,
    val getDailyWeatherForecast: GetDailyWeatherForecast
) : BasePresenter<ExpandedForecastView>() {

    override fun onAttach() {
        observeBackButton()
        retrieveLatestWeatherData()
    }

    private fun retrieveLatestWeatherData() {
        compositeDisposable?.add(getDailyWeatherForecast.execute(coordinate, unixTime)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.updateUi(it.toExpandedForecastViewModel())
            }, Timber::e)
        )
    }

    private fun observeBackButton() {
        view?.let { view ->
            compositeDisposable?.add(
                view.backButtonInputObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { view.closeView() },
                        Timber::e
                    )
            )
        }
    }

    override fun onDetach() { }
}
