package com.example.nft_app.DataBase

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface TokenDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTokens(tokens: ArrayList<Token>): Completable


    @Query("select * from tokens")
    fun getAllTokens(): Maybe<List<Token>>

    @Query("delete from tokens")
    fun clear(): Completable

}