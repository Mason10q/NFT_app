package com.example.nft_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class NftActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var id: TextView
    private lateinit var authorName: TextView
    private lateinit var date: TextView
    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nft)

        val picasso = Picasso.Builder(this)
            .build()

        image = findViewById(R.id.image)
        name = findViewById(R.id.name)
        id = findViewById(R.id.id)
        date = findViewById(R.id.date)

       // date = findViewById(R.id.date)

        val intent = getIntent()
        val itemId = intent.getStringExtra("itemId")

        val tokenData = TokenCache.tokenCache[itemId]


        picasso.load(tokenData?.metadata?.image)
            .fit()
            .centerCrop()
            .into(image)

        name.text = "Standart name"
        id.text = "#${tokenData?.tokenID}"
        date.text = "Added: ${tokenData?.date}"

    }

}