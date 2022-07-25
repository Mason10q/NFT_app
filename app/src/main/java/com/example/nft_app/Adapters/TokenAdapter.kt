package com.example.nft_app.Adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nft_app.R
import com.example.nft_app.DataBase.Token
import com.example.nft_app.inflate
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class TokenAdapter(val picasso: Picasso): RecyclerView.Adapter<TokenAdapter.ViewHolder>() {

    interface ItemClickListener<T>{
        fun onItemClick(position: Int)
    }

    var tokenList = mutableListOf<Token>()

    lateinit var itemClickListener: ItemClickListener<Token>

    override fun getItemCount(): Int = tokenList.size

    fun addItems(list: MutableList<Token>){
        tokenList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val holder = ViewHolder(parent.inflate(R.layout.token_item))

        holder.itemView.setOnClickListener {
            val position: Int = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                fireItemClicked(position)
            }
        }

        return holder
    }


    private fun fireItemClicked(position: Int) =
        itemClickListener.onItemClick(position)


    fun setOnItemClickListener(listener: ItemClickListener<Token>) {
        itemClickListener = listener
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val nft = tokenList[position]

        picasso.load(nft.image)
            .placeholder(R.drawable.ic_nft)
            .fit()
            .centerCrop()
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.image)
        holder.name.text = nft.name
        holder.id.text = nft.tokenId
    }



    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val image: ImageView = item.findViewById(R.id.token_image)
        val name: TextView = item.findViewById(R.id.token_name)
        val id: TextView = item.findViewById(R.id.token_price)
    }

}