package org.ups.greensky

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.ups.greensky.api.ApiConfig
import timber.log.Timber

class GreenSkyApplication : Application(), KodeinAware {

    override val kodein = configureDi()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun getApiConfig(): ApiConfig {
        return object : ApiConfig {
            override val apiKey: String
                get() = "3b05e4a2b981ff0670e5f6dd4603a4d7"
        }
    }

    private fun configureDi(): Kodein {
        return DiConfigurator().configureDi(this, getApiConfig())

    }
}