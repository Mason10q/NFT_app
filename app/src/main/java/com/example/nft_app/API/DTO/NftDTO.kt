package com.example.nft_app.API.DTO

import com.example.nft_app.API.DTO.MetadataDTO
import com.google.android.material.circularreveal.CircularRevealHelper
import com.google.gson.annotations.SerializedName

data class NftDTO(

    @SerializedName("chain")
    val chain : String = "",
    @SerializedName("contract_address")
    val address : String = "",
    @SerializedName("token_id")
    val tokenID : String = "",
    @SerializedName("metadata")
    val metadata : MetadataDTO? = MetadataDTO(),
    @SerializedName("metadata_url")
    val md_url : String = "",
    @SerializedName("file_url")
    val file_url : String = "",
    @SerializedName("animation_url")
    val anim_url : String = "",
    @SerializedName("cached_file_url")
    val cach_url : String = "",
    @SerializedName("cached_animation_url")
    val cach_anim_url : String = "",
    @SerializedName("mint_date")
    val mint_date : String = "",
    @SerializedName("file_information")
    val file_info : InfoDTO = InfoDTO(),
    @SerializedName("updated_date")
    val date : String = ""

)





