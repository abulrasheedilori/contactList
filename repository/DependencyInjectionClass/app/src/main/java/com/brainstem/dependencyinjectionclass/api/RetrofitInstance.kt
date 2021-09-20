package com.brainstem.dependencyinjectionclass.api

import com.brainstem.dependencyinjectionclass.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(logInterceptor).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiUsers::class.java)
    }
//    val api: ApiUsers by lazy {
//        retrofit.create(ApiUsers::class.java)
//    }
}