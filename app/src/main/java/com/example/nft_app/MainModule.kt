package com.example.nft_app

import android.content.Context
import androidx.room.Room
import com.example.nft_app.API.DTO.DTO
import com.example.nft_app.API.TestApi
import com.example.nft_app.Adapters.ViewPagerAdapter
import com.example.nft_app.DataBase.AppDataBase
import com.example.nft_app.DataBase.Repository
import com.example.nft_app.DataBase.TokenDAO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Cache
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule(val context: Context) {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideOkHttp() = OkHttpClient.Builder()
        /*.cache(cache)*/
        .addInterceptor{ chain->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "d725bf24-039d-43a4-9cd3-e85266f0466b")
                /*.cacheControl(
                    CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build()
                )*/
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

    @Singleton
    @Provides
    fun providePicasso() = Picasso.Builder(context)
            .build()

    @Provides
    fun provideViewPagerAdapter(picasso: Picasso) = ViewPagerAdapter(picasso)

    @Provides
    @Singleton
    fun provideAppDataBase() = Room.databaseBuilder(
        context.applicationContext,
        AppDataBase::class.java,
        "app_database"
    ).build()

    @Provides
    fun provideTokenDao(dataBase: AppDataBase) = dataBase.getTokenDAO()

    @Provides
    fun provideRepository(dao: TokenDAO) = Repository(dao)

}