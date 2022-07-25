package com.example.nft_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nft_app.API.TestApi
import com.example.nft_app.DataBase.Repository
import com.example.nft_app.DataBase.Token
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Inject


const val AMOUNT_TOKENS = 16

class MainViewModel : ViewModel() {

    @Inject lateinit var api: TestApi
    @Inject lateinit var repository: Repository

    val tokens = MutableLiveData<ArrayList<Token>>()
    var count: Int = 0
    private var pageNumber: Int = 0

    fun getPageNumber() = pageNumber
    fun setPageNum(newValue: Int) { pageNumber = newValue }

    fun getNfts() = api.getNfts(
        "0x34d85c9CDeB23FA97cb08333b511ac86E1C4E258",
        "ethereum",
        ++pageNumber,
        AMOUNT_TOKENS,
        "metadata"
    ).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({

            val list = Token.buildFromNftList(it.nfts)
            insertTokens(list)
            tokens.postValue(list)

            Log.d("bbbbb", it.toString())
        }, {
            Log.d("aaaaaa", it.toString())
        })


    fun getAllTokens() = repository.getAllContacs()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe{
            tokens.postValue(it as ArrayList<Token>)
        }

    fun insertTokens(tokens: ArrayList<Token>) = repository.insertTokens(tokens)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe()

    fun clear() = repository.clear()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe({
            Log.d("memmm", "clear complete")
        }, {
            Log.d("memmm", "clear failed")
        })


}