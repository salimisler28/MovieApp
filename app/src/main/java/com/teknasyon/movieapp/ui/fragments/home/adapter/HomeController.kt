package com.teknasyon.movieapp.ui.fragments.home.adapter

import com.airbnb.epoxy.EpoxyController
import com.teknasyon.movieapp.app.network.dto.TvShowDto
import com.teknasyon.movieapp.ui.util.PageStates
import com.teknasyon.movieapp.ui.util.loadingItem
import com.teknasyon.movieapp.ui.util.smallLoadingItem

class HomeController : EpoxyController() {
    var data: List<TvShowDto>? = null
    var state: PageStates? = null

    var onTvShowItemClickListener: ((TvShowDto?) -> Unit)? = null
    var newPageListener: (() -> Unit)? = null


    override fun buildModels() {
        if (state == PageStates.SUCCESS
            || state == PageStates.NEXT_PAGE_LOADING
            || state == PageStates.NEXT_PAGE
            || state == PageStates.NEXT_PAGE_ERROR
        ) {
            data?.forEachIndexed { index, item ->
                tvShowItem {
                    id(item.id)
                    tvShowDto(item)
                    onTvShowItemClickListener(onTvShowItemClickListener)
                    currentIndex(index)
                    totalItem(data?.size)
                    newPageListener(newPageListener)
                    spanSizeOverride { totalSpanCount, position, itemCount -> 1 }
                }
            }

            if (state == PageStates.NEXT_PAGE_LOADING) {
                smallLoadingItem {
                    id("smallItem")
                    spanSizeOverride { totalSpanCount, position, itemCount -> 2 }
                }
            }

            if (state == PageStates.NEXT_PAGE_ERROR) {
                // TODO
            }
        }

        if (state == PageStates.LOADING) {
            loadingItem {
                id("laoding")
                spanSizeOverride { totalSpanCount, position, itemCount -> 2 }
            }
        }

        if (state == PageStates.ERROR) {
            // TODO
        }

        if (state == PageStates.NULL_DATA) {
            // TODO
        }
    }
}