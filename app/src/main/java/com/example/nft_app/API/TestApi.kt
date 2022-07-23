package com.example.nft_app.API

import com.example.nft_app.API.DTO.DTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface TestApi {

    @GET("v0/nfts/{contract_address}")
    fun getNfts(

        @Path("contract_address")
        contract : String,
        @Query("chain")
        chain : String,
        @Query("page_number")
        number: Int,
        @Query("page_size")
        size : Int,
        @Query("include")
        include : String

    ) : Single< DTO >


    @GET("/ipfs")
    fun getImage(
        @Query("cid")
        cid : String
    )

}