package com.example.nft_app.Adapters

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nft_app.R
import com.example.nft_app.Token
import com.example.nft_app.inflate
import com.squareup.picasso.Picasso


class TokenAdapter(val picasso: Picasso): RecyclerView.Adapter<TokenAdapter.ViewHolder>() {

    interface ItemClickListener<T>{
        fun onItemClick(position: Int, item: Token, image: ImageView)
    }

    var tokenList = mutableListOf<Token>()

    lateinit var itemClickListener: ItemClickListener<Token>

    override fun getItemCount(): Int = tokenList.size

    fun addItem(item: Token){
        tokenList.add(item)
        notifyDataSetChanged()
    }

    fun addItems(list: List<Token>){
        tokenList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val holder = ViewHolder(parent.inflate(R.layout.token_item))

        holder.itemView.setOnClickListener { v ->
            val position: Int = holder.getAdapterPosition()
            if (position != RecyclerView.NO_POSITION) {
                fireItemClicked(position, tokenList.get(position), holder)
            }
        }

        return holder
    }


    private fun fireItemClicked(position: Int, item: Token, holder: ViewHolder) =
        itemClickListener.onItemClick(position, item, holder.image)


    fun setOnItemClickListener(listener: ItemClickListener<Token>) {
        itemClickListener = listener
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        picasso.load(tokenList[position].image)
            .placeholder(R.drawable.ic_nft)
            .fit()
            .centerCrop()
            .into(holder.image)
        holder.name.text = tokenList[position].name
        holder.price.text = tokenList[position].id
    }



    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val image: ImageView = item.findViewById(R.id.token_image)
        val name: TextView = item.findViewById(R.id.token_name)
        val price: TextView = item.findViewById(R.id.token_price)
    }

}