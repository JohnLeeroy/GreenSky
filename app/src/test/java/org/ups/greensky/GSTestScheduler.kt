package org.ups.greensky

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.ups.greensky.common.rx.GSSchedulers

class GSTestScheduler : GSSchedulers {

    val defaultScheduler = TestScheduler()

    override fun networkScheduler(): Scheduler {
        return defaultScheduler
    }

    override fun computationScheduler(): Scheduler {
        return defaultScheduler
    }

    override fun uiScheduler(): Scheduler {
        return defaultScheduler
    }


}