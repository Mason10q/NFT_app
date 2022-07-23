package com.example.nft_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflater() = LayoutInflater.from(context)

fun ViewGroup.inflate(id : Int) = LayoutInflater.from(context).inflate(id, this, false)

fun RecyclerView.onScrollToEnd(
    onScrollNearEnd: (Unit) -> Unit
) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!recyclerView.canScrollVertically(1)) {
            onScrollNearEnd(Unit)
        }
    }
})