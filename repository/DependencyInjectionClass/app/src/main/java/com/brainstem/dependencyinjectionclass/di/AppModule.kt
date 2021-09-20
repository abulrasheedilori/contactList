package com.brainstem.dependencyinjectionclass.di

import com.brainstem.dependencyinjectionclass.api.ApiUsers
import com.brainstem.dependencyinjectionclass.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //used to specify di application scope
object AppModule {

    //create single instance of retrofit
    @Singleton
    @Provides
    fun retrofitInstance(): ApiUsers {
        val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(logInterceptor).build())
            .baseUrl(BASE_URL)
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
            .create(ApiUsers::class.java)

    }

    //instance of retrofit to interface
//    @Singleton
//    @Provides
//    fun InterfaceInstance(retrofit: Retrofit): ApiUsers{
//        return retrofit.create(ApiUsers::class.java)
//    }
}