package org.ups.greensky.api.darksky

import org.ups.greensky.api.ApiConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class DarkSkyApiFactory() {

    fun make(apiConfig: ApiConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl.plus(apiConfig.apiKey))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    companion object {
        private const val baseUrl = "https://api.darksky.net/forecast/"
    }
}