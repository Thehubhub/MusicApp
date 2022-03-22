package com.example.musicappassign3.rest

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader("","")
        }.build()

        return chain.proceed(request)
    }
}