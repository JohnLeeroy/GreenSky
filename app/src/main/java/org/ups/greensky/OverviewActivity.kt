package org.ups.greensky

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.ups.greensky.mvp.BaseMVPActivity
import org.ups.greensky.mvp.PresenterProvider


class OverviewActivity : BaseMVPActivity<OverviewView,OverviewPresenter>(), OverviewView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onRefresh(): Observable<Unit> {
        return swipeToRefreshLayout.refreshes()
    }

    override fun onItemClicked(): Observable<OverviewInputEvent> {
        return Observable.empty()
    }

    override val presenterProvider: PresenterProvider<OverviewView, OverviewPresenter>
        get() = OverviewPresenterFactory()

}

