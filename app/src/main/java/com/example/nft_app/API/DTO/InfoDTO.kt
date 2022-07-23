package com.example.nft_app.API.DTO

import com.google.gson.annotations.SerializedName

data class InfoDTO(
    @SerializedName("height")
    val height : Int = 0,
    @SerializedName("width")
    val width : Int = 0,
    @SerializedName("file_size")
    val size : Int = 0
)