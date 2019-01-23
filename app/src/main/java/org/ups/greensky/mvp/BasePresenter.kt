package org.ups.greensky.mvp

import android.os.Bundle
import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<VIEW : BaseView> : AndroidPresenterContract {

    protected var compositeDisposable: CompositeDisposable? = null
    protected var view: VIEW? = null

    @CallSuper
    fun attach(view: VIEW) {
        compositeDisposable = CompositeDisposable()
        this.view = view
        onAttach()
    }

    @CallSuper
    fun detach() {
        compositeDisposable!!.dispose()
        compositeDisposable = null
        view = null
        onDetach()
    }

    override fun load(bundle: Bundle) {

    }

    override fun save(bundle: Bundle): Bundle {
        return Bundle.EMPTY
    }
}
