package org.ups.greensky.mvp

abstract class PresenterProvider<VIEW : BaseView, out PRESENTER : BasePresenter<VIEW>> {

  abstract fun provide(): PRESENTER

}