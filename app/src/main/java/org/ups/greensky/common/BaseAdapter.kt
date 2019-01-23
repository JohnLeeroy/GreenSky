package org.ups.greensky.common

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class BaseAdapter<MODEL, INPUT_EVENT : BaseInputEvent, VIEWHOLDER : BaseViewHolder<MODEL, INPUT_EVENT>> :
    RecyclerView.Adapter<VIEWHOLDER>() {

    protected var data = mutableListOf<MODEL>()
    protected val inputEventSubject = PublishSubject.create<INPUT_EVENT>()
    protected val compositeDisposable = CompositeDisposable()

    override fun getItemCount(): Int {
        return data.count()
    }

    abstract fun addOrUpdateItems(items: List<MODEL>)

    override fun onBindViewHolder(holder: VIEWHOLDER, position: Int) {
        holder.bind(data[position])
    }

    public fun dispose() {
        compositeDisposable.dispose()
    }

    public fun getInputObservable(): Observable<INPUT_EVENT> {
        return inputEventSubject
    }

    protected fun observeViewHolderInput(inputEventObservable: Observable<INPUT_EVENT>) {
        compositeDisposable.add(inputEventObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<INPUT_EVENT>() {
                override fun onComplete() { }

                override fun onNext(inputEvent: INPUT_EVENT) {
                    inputEventSubject.onNext(inputEvent)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
        )
    }
}