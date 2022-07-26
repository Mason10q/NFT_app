package com.example.nft_app.API.DTO

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class DTO(

    @SerializedName("response")
    val response : String = "",
    @SerializedName("nfts")
    val nfts : ArrayList<NftDTO> = arrayListOf(),
    @SerializedName("contract")
    val contract : ContractDTO,
    @SerializedName("total")
    val total : Int = 0,
    @SerializedName("status")
    val status : String = "ADDED",
    @SerializedName("status_massage")
    val status_massage : String = ""

)