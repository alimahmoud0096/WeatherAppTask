package com.alihafez.core.data.remote

import com.alihafez.core.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(APP_ID, API_KEY)
            .addQueryParameter(UNITS, METRIC)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(request)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val APP_ID = "appid"
        private const val UNITS = "units"
        private const val METRIC = "metric"
    }
}