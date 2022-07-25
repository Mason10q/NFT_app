package com.example.nft_app.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nft_app.API.DTO.NftDTO
import java.io.Serializable

@Entity(tableName = "tokens")
data class Token(


    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "name")
    var name: String,

    @PrimaryKey
    @ColumnInfo(name = "token_id")
    var tokenId: String,

    @ColumnInfo(name = "date")
    var date: String

): Serializable{


    companion object{

        @JvmStatic
        fun buildFromNftList(list: ArrayList<NftDTO>): ArrayList<Token>{

            var tokenList = arrayListOf<Token>()

            list.forEach {
                tokenList.add(Token(it.metadata?.image!!, it.metadata.name!!, it.tokenID, it.date))
            }

            return tokenList
        }

    }
}