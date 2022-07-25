package com.example.nft_app.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.nft_app.*
import com.example.nft_app.Adapters.ViewPagerAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

class TokenActivity : AppCompatActivity() {

    private val viewModel: MainViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(MainViewModel::class.java)

    var amount = 0

    private lateinit var viewPager: ViewPager2

    @Inject lateinit var adapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token)

        with(MainComponent.init(this)) {
            inject(this@TokenActivity)
            inject(viewModel)
        }


        val currentItemPos = intent.getIntExtra("position", 1)

        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = adapter

        if(adapter.itemCount == 0) {
            viewModel.getAllTokens()
        }

        viewModel.tokens.observe(this) {
            adapter.addItems(it)
            viewPager.setCurrentItem(currentItemPos, false)
            amount = adapter.itemCount
            Log.d("memmm", "set position ${adapter.itemCount}")
        }

        viewPager.onSwipeToEnd {
            viewModel.getNfts()
            Log.d("memmm", "end")
        }
    }

}

