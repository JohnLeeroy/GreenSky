package org.ups.greensky.api.darksky

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.ups.greensky.api.ApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DarkSkyApiFactory {

    fun make(apiConfig: ApiConfig): DarkSkyApi {
        val url = String.format(baseUrl, apiConfig.apiKey)
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val darkSkyService = retrofit.create(DarkSkyService::class.java)
        return DarkSkyApi(darkSkyService)
    }

    companion object {
        private const val baseUrl = "https://api.darksky.net/forecast/%s/"
    }
}