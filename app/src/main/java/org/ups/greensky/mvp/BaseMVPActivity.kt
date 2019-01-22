package org.ups.greensky.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseMVPActivity<VIEW : BaseView, PRESENTER : BasePresenter<VIEW>> : AppCompatActivity(), BaseView {

    private lateinit var lifecycleDelegateContract: LifecycleDelegateContract

    protected abstract val presenterProvider: PresenterProvider<VIEW, PRESENTER>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleDelegateContract = LifecycleDelegateImpl(this as VIEW, presenterProvider)
        lifecycleDelegateContract.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        lifecycleDelegateContract.onPause()
    }

    override fun onResume() {
        super.onResume()
        lifecycleDelegateContract.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleDelegateContract.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lifecycleDelegateContract.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lifecycleDelegateContract.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycleDelegateContract.onActivityResult(requestCode, requestCode, data)
    }
}
