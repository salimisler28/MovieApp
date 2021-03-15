package com.teknasyon.movieapp.ui.util

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.teknasyon.movieapp.R

@EpoxyModelClass(layout = R.layout.item_small_loading)
abstract class SmallLoadingItem : EpoxyModelWithHolder<SmallLoadingItem.ViewHolder>() {
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.apply {}
    }

    class ViewHolder : EpoxyHolder() {
        override fun bindView(itemView: View) {}
    }
}