package org.ups.greensky.common.rx

import io.reactivex.Scheduler

interface GSSchedulers {
    fun networkScheduler() : Scheduler
    fun computationScheduler() : Scheduler
    fun uiScheduler() : Scheduler
}