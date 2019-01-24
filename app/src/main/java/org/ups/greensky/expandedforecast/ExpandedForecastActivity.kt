package org.ups.greensky.expandedforecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_expanded_forecast.*
import org.ups.greensky.GreenSkyApplication
import org.ups.greensky.R
import org.ups.greensky.core.model.Coordinate
import org.ups.greensky.mvp.BaseMVPActivity
import org.ups.greensky.mvp.PresenterProvider

class ExpandedForecastActivity : BaseMVPActivity<ExpandedForecastView, ExpandedForecastPresenter>(),
    ExpandedForecastView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanded_forecast)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun updateUi(viewModel: ExpandedForecastViewModel) {
        toolbar.title = viewModel.currentTime
        summaryLabel.text = viewModel.summary
        highTempLabel.text = viewModel.tempHigh
        lowTempLabel.text = viewModel.tempLow
        hottestAtLabel.text = viewModel.hottestAt
        coldestAtLabel.text = viewModel.coldestAt
        weatherImage.setImageResource(viewModel.weatherIconResId)
    }
    override fun backButtonInputObservable(): Observable<Unit> {
        return toolbar.navigationClicks()
    }

    override fun closeView() {
        finish()
    }

    override val presenterProvider: PresenterProvider<ExpandedForecastView, ExpandedForecastPresenter>
        get() = ExpandedForecastPresenterFactory((application as GreenSkyApplication).kodein, intent.extras)

    companion object {
        val LATITUDE_ARG = ExpandedForecastActivity.javaClass.name.plus("LATITUDE_ARG")
        val LONGITUDE_ARG = ExpandedForecastActivity.javaClass.name.plus("LONGITUDE_ARG")
        val UNIX_TIME_ARG = ExpandedForecastActivity.javaClass.name.plus("UNIX_TIME_ARG")

        fun start(
            context: Context,
            coordinate: Coordinate,
            unixTime: Long
        ) {
            val intent = Intent(context, ExpandedForecastActivity::class.java)
            val bundle = Bundle()
            bundle.putDouble(LATITUDE_ARG, coordinate.latitude)
            bundle.putDouble(LONGITUDE_ARG, coordinate.longitude)
            bundle.putLong(UNIX_TIME_ARG, unixTime)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
