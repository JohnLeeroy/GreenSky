package org.ups.greensky

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.ups.greensky.api.WeatherApiContract
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.core.model.weather.component.BasicWeatherData
import org.ups.greensky.core.model.weather.component.TemperatureRange
import org.ups.greensky.core.model.weather.component.precipitation.Precipitation
import org.ups.greensky.core.model.weather.component.precipitation.PrecipitationType
import org.ups.greensky.core.model.weather.snapshot.CurrentWeatherSnapshot
import org.ups.greensky.core.model.weather.snapshot.DailyWeatherSnapshot
import org.ups.greensky.interactor.GetCurrentWeather
import org.ups.greensky.interactor.GetCurrentWeeklyForecast
import org.ups.greensky.overview.OverviewPresenter
import org.ups.greensky.overview.OverviewView
import org.ups.greensky.overview.recycler.ExpandWeatherForecastEvent
import org.ups.greensky.overview.recycler.OverviewInputEvent

class OverviewPresenterTest {

    private lateinit var presenter: OverviewPresenter

    private val weatherApiMock = mock(WeatherApiContract::class.java)
    private val getCurrentWeather = GetCurrentWeather(weatherApiMock)
    private val getCurrentWeeklyForecast = GetCurrentWeeklyForecast(weatherApiMock)
    private val view = mock(OverviewView::class.java)
    private val testScheduler = GSTestScheduler()

    private lateinit var inputSubject : PublishSubject<OverviewInputEvent>
    private lateinit var refreshSubject : PublishSubject<Unit>

    //TODO-JLI currently matches San Diego && hard value in presenter inplementation
    //  Should refactor how coordinate is injected into presenter
    private val coordinateDummy = Coordinate(32.8242404, -117.389167)

    @Before
    fun setup() {
        presenter = OverviewPresenter(getCurrentWeather, getCurrentWeeklyForecast, testScheduler)
        val precipitationDummy = Precipitation(PrecipitationType.NONE, 0f, 0f)
        val currentWeatherDummy = CurrentWeatherSnapshot(
            BasicWeatherData(0f,0f, precipitationDummy),
            0,
            coordinateDummy,
            "",
            emptySet())

        val dailyWeatherDummy = DailyWeatherSnapshot(
            TemperatureRange(0f, 0, 0f, 0),
            TemperatureRange(0f, 0, 0f, 0),
            precipitationDummy,
            0,
            coordinateDummy,
            "",
            emptySet())

        `when`(weatherApiMock.getCurrentWeather(any(), anyLong()))
            .thenReturn(Observable.just(currentWeatherDummy))

        `when`(weatherApiMock.getCurrentWeeklyForecast(any()))
            .thenReturn(Observable.just(arrayListOf(dailyWeatherDummy)));

        inputSubject = PublishSubject.create<OverviewInputEvent>()
        `when`(view.onItemClicked()).thenReturn(inputSubject)

        refreshSubject = PublishSubject.create<Unit>()
        `when`(view.onRefresh()).thenReturn(refreshSubject)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Test
    fun testOnRefresh() {
        presenter.attach(view)
//        val expandEventDummy = ExpandWeatherForecastEvent(0)
//        inputSubject.onNext(expandEventDummy)
        refreshSubject.onNext(Unit)
        testScheduler.defaultScheduler.triggerActions()
        verify(view, atLeastOnce()).addOrUpdateOverviewAdapter(any())
    }

    @Test
    fun testOpenExpandedWeatherView() {
        presenter.attach(view)
        val expandEventDummy = ExpandWeatherForecastEvent(1234)
        inputSubject.onNext(expandEventDummy)
        testScheduler.defaultScheduler.triggerActions()
        verify(view, atLeastOnce()).openExpandedForecastView(coordinateDummy, expandEventDummy.time)
    }


}
