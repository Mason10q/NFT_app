package com.example.nft_app.Adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nft_app.R
import com.example.nft_app.DataBase.Token
import com.example.nft_app.inflate
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ViewPagerAdapter @Inject constructor(
    private val picasso: Picasso
) : ListAdapter<Token, ViewPagerAdapter.PagerViewHolder>(TokenComparator()){

    private val pagerItemList = mutableListOf<Token>()

    fun addItems(list: MutableList<Token>){
        pagerItemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pagerItemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(parent.inflate(R.layout.token_info_item))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

        val nft = pagerItemList[position]

        picasso.load(nft.image)
            .fit()
            .centerCrop()
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.image)

        holder.name.text = "Standart name"
        holder.id.text = "#${nft?.tokenId}"
        holder.date.text = "Added: ${nft?.date}"
    }


    class PagerViewHolder(item: View): RecyclerView.ViewHolder(item){
        val image: ImageView = item.findViewById(R.id.image)
        val name: TextView = item.findViewById(R.id.name)
        val id:TextView = item.findViewById(R.id.id)
        val date: TextView = item.findViewById(R.id.date)
    }


    class TokenComparator: DiffUtil.ItemCallback<Token>(){
        override fun areItemsTheSame(oldItem: Token, newItem: Token): Boolean =
            (oldItem.tokenId == newItem.tokenId)

        override fun areContentsTheSame(oldItem: Token, newItem: Token)=
            oldItem.image == newItem.image

    }

}