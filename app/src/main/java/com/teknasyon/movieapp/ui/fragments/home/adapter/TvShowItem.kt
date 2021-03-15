package com.teknasyon.movieapp.ui.fragments.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.teknasyon.movieapp.R
import com.teknasyon.movieapp.app.network.dto.TvShowDto
import com.teknasyon.movieapp.app.util.loadImage
import kotlinx.android.synthetic.main.item_tv_show.view.*

@EpoxyModelClass(layout = R.layout.item_tv_show)
abstract class TvShowItem : EpoxyModelWithHolder<TvShowItem.ViewHolder>() {
    @EpoxyAttribute
    var tvShowDto: TvShowDto? = null

    @EpoxyAttribute
    var onTvShowItemClickListener: ((TvShowDto?) -> Unit)? = null

    @EpoxyAttribute
    var newPageListener: (() -> Unit)? = null

    @EpoxyAttribute
    var currentIndex: Int? = null

    @EpoxyAttribute
    var totalItem: Int? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        holder.apply {
            cardTvShow.setOnClickListener {
                onTvShowItemClickListener?.invoke(tvShowDto)
            }
            imgTvShow.loadImage(tvShowDto?.poster_path)
            txTvShowName.text = tvShowDto?.name
            txTvShowRating.text = tvShowDto?.vote_average.toString()

            if (totalItem?.minus(10) == currentIndex) {
                newPageListener?.invoke()
            }
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var cardTvShow: CardView
        lateinit var imgTvShow: ImageView
        lateinit var txTvShowName: TextView
        lateinit var txTvShowRating: TextView

        override fun bindView(itemView: View) {
            cardTvShow = itemView.cardTvShow
            imgTvShow = itemView.imgTvShow
            txTvShowName = itemView.txTvShowName
            txTvShowRating = itemView.txTvShowRating
        }
    }
}