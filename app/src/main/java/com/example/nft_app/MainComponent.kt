package com.example.nft_app

import android.content.Context
import com.example.nft_app.Activities.MainActivity
import com.example.nft_app.Activities.TokenActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(viewModel: MainViewModel)
    fun inject(activity: TokenActivity)
    fun inject(activity: MainActivity)

    companion object{

       fun init(context: Context): MainComponent = DaggerMainComponent.builder()
           .mainModule(MainModule(context))
           .build()

    }

}