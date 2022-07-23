package com.example.nft_app

import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(viewModel: MainViewModel)

    companion object{

        fun init() = DaggerMainComponent.builder()
            .mainModule(MainModule())
            .build()

    }

}