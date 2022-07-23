package com.example.nft_app

import com.example.nft_app.API.DTO.DTO
import com.example.nft_app.API.TestApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideOkHttp() = OkHttpClient.Builder()
        .addInterceptor{ chain->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "d725bf24-039d-43a4-9cd3-e85266f0466b")
                .build()

            return@addInterceptor chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().apply{level = HttpLoggingInterceptor.Level.BODY})
        .build()

    @Provides
    fun provideMainApi(client: OkHttpClient, gson: Gson): TestApi = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl("https://api.nftport.xyz/")
        .build()
        .create(TestApi::class.java)
}