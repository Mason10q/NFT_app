package com.example.nft_app

import android.app.Application
import android.util.Log

class App: Application() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        Log.d("memmm", "onTerminate")
        super.onTerminate()
    }
}