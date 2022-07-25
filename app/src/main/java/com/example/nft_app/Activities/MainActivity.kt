package com.example.nft_app.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft_app.*
import com.example.nft_app.Adapters.TokenAdapter
import com.example.nft_app.DataBase.Token
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TokenAdapter.ItemClickListener<Token> {

    private val viewModel: MainViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(MainViewModel::class.java)

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TokenAdapter
    @Inject lateinit var picasso: Picasso


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(MainComponent.init(this)){
            inject(viewModel)
            inject(this@MainActivity)
        }

        viewModel.clear()

        recycler = findViewById(R.id.recycler)
        adapter = TokenAdapter(picasso)
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        adapter.setOnItemClickListener(this)

        viewModel.getNfts()

        recycler.onScrollToEnd {
            viewModel.getNfts()
        }

        viewModel.tokens.observe(this){
            adapter.addItems(it)
        }

    }

    override fun onItemClick(position: Int) =
        with(Intent(this, TokenActivity::class.java)){
            putExtra("position", position)
            startActivity(this)
        }

}
