package org.ups.greensky.overview

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
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
        refreshData()
        observeRefresh()
        view?.let {
            it.onItemClicked()
                .subscribe({

                }, Timber::e)
        }
    }

    override fun onDetach() {

    }

    private fun refreshData() {
        val coordinate = Coordinate(47.608013, -122.335167)
        compositeDisposable?.add(Observable.zip(
            getCurrentWeather(coordinate),
            getCurrentWeatherWeeklyForecast(coordinate),
            BiFunction<OverviewItem, List<OverviewItem>, List<OverviewItem>> { one, two ->
                mergeWeatherResults(one, two)
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.addOrUpdateOverviewAdapter(it)
                view?.hideRefreshIndicator()
            }, this::handleError)
        )
    }

    fun mergeWeatherResults(
        currentWeatherItem: OverviewItem,
        weeklyForecastItems: List<OverviewItem>
    ): List<OverviewItem> {
        val results = mutableListOf<OverviewItem>()
        results.add(currentWeatherItem)
        results.addAll(weeklyForecastItems)
        return results
    }

    private fun handleError(throwable: Throwable) {
        Timber.e(throwable)
        throwable.message?.let { view?.showError(it) }
    }

    private fun getCurrentWeather(coordinate: Coordinate): Observable<OverviewItem> {
        return getCurrentWeather.execute(
            coordinate,
            System.currentTimeMillis() / 1000
        )
            .map { it.mapToWeatherItem() }
    }

    private fun getCurrentWeatherWeeklyForecast(coordinate: Coordinate): Observable<List<OverviewItem>> {
        return getCurrentWeeklyForecast.execute(coordinate)
            .map {
                val list = mutableListOf<OverviewItem>()
                it.forEach {
                    list.add(it.mapToWeatherItem())
                }
                return@map list
            }
    }

    private fun observeRefresh() {
        view?.let {
            it.onRefresh()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ refreshData() }, Timber::e)
        }
    }

}