package com.example.nft_app

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

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


fun ViewPager2.onSwipeToEnd(
    onScrollNearEnd: (Unit) -> Unit
)= registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        Log.d("memmm", "onPAgeSelected ${adapter?.itemCount ?: 0}")
        if(position == (adapter?.itemCount ?: 0) - 2)
            onScrollNearEnd(Unit)
    }
})
