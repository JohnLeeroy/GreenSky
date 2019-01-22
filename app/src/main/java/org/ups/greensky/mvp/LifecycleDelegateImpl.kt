package org.ups.greensky.mvp

import android.content.Intent
import android.os.Bundle

class LifecycleDelegateImpl<V : BaseView, P : BasePresenter<V>>(
    private val view: V,
    presenterProvider: PresenterProvider<V, P>
) : LifecycleDelegateContract {

    private var presenter: P = presenterProvider.provide()

    override fun onCreate(bundle: Bundle?) {

    }

    override fun onDestroy() {
    }

    override fun onPause() {
        onViewDetached()
    }

    override fun onResume() {
        onViewAttached()
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    override fun onViewAttached() {
        presenter.attach(view)
    }

    override fun onViewDetached() {
        presenter.detach()
    }

    override fun save(outBundle: Bundle) {
        presenter.save(outBundle)
    }

    override fun load(bundle: Bundle) {
        presenter.load(bundle)
    }
}