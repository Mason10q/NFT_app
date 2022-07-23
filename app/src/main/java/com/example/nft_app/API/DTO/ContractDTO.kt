package com.example.nft_app.API.DTO

import com.google.gson.annotations.SerializedName

data class ContractDTO(

    @SerializedName("name")
    val name : String = "",
    @SerializedName("symbol")
    val symbol : String = "",
    @SerializedName("type")
    val type : String = "",
    @SerializedName("metadata")
    val metadata : MdDTO,

    )