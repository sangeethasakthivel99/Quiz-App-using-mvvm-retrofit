package com.sangeetha.quiz

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val mRetrofitClient: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl("https://opentdb.com/")
            .build()
    }

    fun getNetworkClient(): Retrofit = mRetrofitClient
}