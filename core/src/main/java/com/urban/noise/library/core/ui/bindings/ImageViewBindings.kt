package com.urban.noise.library.core.ui.bindings

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
fun ImageView.imageUrl(url: String?, @DrawableRes placeholderId: Int?) {
    Glide
        .with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(placeholderId?.let {
            ContextCompat.getDrawable(context, it)
        })
        .centerCrop()
        .into(this)
}
