package com.everest.pagerlistdrawer

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "https://reqres.in/"

    fun getClient(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL).build()
    }
}