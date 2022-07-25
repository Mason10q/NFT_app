package com.example.nft_app.DataBase

class Repository(val dao: TokenDAO) {

    fun insertTokens(tokens: ArrayList<Token>) = dao.insertTokens(tokens)

    fun getAllContacs() = dao.getAllTokens()

    fun clear() = dao.clear()

}