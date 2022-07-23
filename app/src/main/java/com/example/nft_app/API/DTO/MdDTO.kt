package com.example.nft_app.API.DTO

import com.google.gson.annotations.SerializedName

data class MdDTO(

    @SerializedName("description")
    val desc : String = "",
    @SerializedName("thumbnail_url")
    val thmb_url : String = "",
    @SerializedName("cached_thumbnail_url")
    val cached_thmb_url : String = "",
    @SerializedName("banner_url")
    val banner_url : String = "",
    @SerializedName("cached_banner_url")
    val cached_banner_url : String = ""

)