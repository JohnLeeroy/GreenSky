package org.ups.greensky.overview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.android.closestKodein
import org.ups.greensky.GreenSkyApplication
import org.ups.greensky.R
import org.ups.greensky.mvp.BaseMVPActivity
import org.ups.greensky.mvp.PresenterProvider
import org.ups.greensky.overview.recycler.OverviewAdapter
import org.ups.greensky.overview.recycler.OverviewInputEvent


class OverviewActivity : BaseMVPActivity<OverviewView, OverviewPresenter>(),
    OverviewView {
    private val adapter = OverviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.adapter = adapter
    }

    override fun setLocationLabel(text: String) {
        toolbar.title = text
    }

    override fun onRefresh(): Observable<Unit> {
        return swipeToRefreshLayout.refreshes()
    }

    override fun onItemClicked(): Observable<OverviewInputEvent> {
        return Observable.empty()
    }

    override val presenterProvider: PresenterProvider<OverviewView, OverviewPresenter>
        get() = OverviewPresenterFactory((application as GreenSkyApplication).kodein)

    override fun onDestroy() {
        super.onDestroy()
        adapter.dispose()
    }
}

