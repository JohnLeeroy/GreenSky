package org.ups.greensky.common.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerImpl : org.ups.greensky.common.rx.GSSchedulers{

    override fun networkScheduler(): Scheduler {
        return Schedulers.newThread()
    }

    override fun computationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    override fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}