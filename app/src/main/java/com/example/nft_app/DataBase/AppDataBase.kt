package com.example.nft_app.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Token::class], version = 1, exportSchema = true)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getTokenDAO(): TokenDAO
}