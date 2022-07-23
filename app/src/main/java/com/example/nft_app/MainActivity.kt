package com.example.nft_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft_app.Adapters.TokenAdapter
import com.example.nft_app.TokenCache.tokenCache
import com.squareup.picasso.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(MainViewModel::class.java)

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TokenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainComponent.init().inject(viewModel)

        val picasso = Picasso.Builder(this)
            .build()

        val intent = Intent(this, NftActivity::class.java)

        recycler = findViewById(R.id.recycler)
        adapter = TokenAdapter(picasso)
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter


        adapter.setOnItemClickListener(object: TokenAdapter.ItemClickListener<Token>{

            override fun onItemClick(position: Int, item: Token, image: ImageView) {
                intent.putExtra("itemId", item.id)

                val options = ViewCompat.getTransitionName(image)?.let {
                    ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this@MainActivity, image, it)
                }

                startActivity(intent, options?.toBundle())
            }

        })


        viewModel.photos.observe(this){
            adapter.addItems(it)
        }

        viewModel.getNfts()

        recycler.onScrollToEnd {
            viewModel.getNfts()
        }

    }


    override fun onStop(){
        super.onStop()

        this.cacheDir.deleteRecursively()
    }

}