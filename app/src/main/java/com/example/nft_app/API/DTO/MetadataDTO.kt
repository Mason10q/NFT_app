package com.example.nft_app.API.DTO

import com.google.gson.annotations.SerializedName

data class MetadataDTO (

    @SerializedName("description")
    val description : String? = "",
    @SerializedName("background_color")
    val bg_color : String? = "",
    @SerializedName("external_url")
    val ext_url : String? = "",
    @SerializedName("image")
    val image: String? = "https://www.imgonline.com.ua/examples/bee-on-daisy.jpg",
    @SerializedName("name")
    val name : String? = "",
    @SerializedName("animation_url")
    val anim_url : String? = ""

)