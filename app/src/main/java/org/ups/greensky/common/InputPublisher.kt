package org.ups.greensky.common

import io.reactivex.Observable

interface InputPublisher<EVENT : BaseInputEvent> {
    fun getInputObservable() : Observable<EVENT>
}