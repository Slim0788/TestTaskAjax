package com.testtask.contacts.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.testtask.contacts.R

object BindingAdapters {

    @BindingAdapter("app:url")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .circleCrop()
            .placeholder(R.drawable.ic_account)
            .into(view)
    }

    @BindingAdapter("app:progress")
    @JvmStatic
    fun isProgress(layout: SwipeRefreshLayout, isProgress: Boolean) {
        layout.isRefreshing = isProgress
    }
}