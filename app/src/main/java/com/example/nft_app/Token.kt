package com.example.nft_app

import com.example.nft_app.API.DTO.NftDTO

class Token(val image: String?, val name: String?, val id: String) {


    companion object{

        @JvmStatic
        fun buildFromDto(list: ArrayList<NftDTO>): ArrayList<Token>{

            var tokenList = arrayListOf<Token>()

            list.forEach {
                tokenList.add(Token(it.metadata?.image, it.metadata?.name, it.tokenID))
            }

            return tokenList
        }

        @JvmStatic
        fun getTokenById(list: ArrayList<NftDTO>, id: String): NftDTO{

            list.forEach{
                if(it.tokenID == id)
                    return it
            }

            return NftDTO()

        }

        @JvmStatic
        fun buildFromNft(nft: NftDTO): Token
        = Token(nft.metadata?.image, nft.metadata?.name, nft.tokenID)
    }
}