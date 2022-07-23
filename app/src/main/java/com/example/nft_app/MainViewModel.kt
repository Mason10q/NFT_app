package com.example.nft_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nft_app.API.TestApi
import com.example.nft_app.Adapters.TokenAdapter
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject lateinit var api: TestApi

    val photos = MutableLiveData<ArrayList<Token>>()
    private var pageNumber: Int = 1


    fun getNfts() = api.getNfts(
        "0x34d85c9CDeB23FA97cb08333b511ac86E1C4E258",
        "ethereum",
        ++pageNumber,
        16,
        "metadata"
    ).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({
            val list = Token.buildFromDto(it.nfts)
            photos.postValue(list)

            it.nfts.forEach{
                TokenCache.tokenCache[it.tokenID] = it
            }

            Log.d("bbbbb", it.toString())
        }, {
            Log.d("aaaaaa", it.toString())
        })

}