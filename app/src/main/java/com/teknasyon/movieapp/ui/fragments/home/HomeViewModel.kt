package com.teknasyon.movieapp.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.movieapp.app.base.Resource
import com.teknasyon.movieapp.app.network.dto.TvShowDto
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowsRequest
import com.teknasyon.movieapp.app.network.response.GetPopularTvShowsResponse
import com.teknasyon.movieapp.app.repository.TvRepository
import com.teknasyon.movieapp.ui.util.PageStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {
    private var currentPage = 1
    private var totalPage: Int? = 0

    var response: Resource<GetPopularTvShowsResponse>? = null
    var tvShows : MutableList<TvShowDto>? = null
    val pageStateObserver = MutableLiveData<PageStates>()

    init {
        getPopularTvShows(
            GetPopularTvShowsRequest(page = currentPage)
        )
    }

    private fun getPopularTvShows(getPopularTvShowsRequest: GetPopularTvShowsRequest) =
        viewModelScope.launch {
            tvRepository.getPopularTvShows(getPopularTvShowsRequest).collect {
                response = it

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        totalPage = it.data?.total_pages
                        tvShows = it.data?.results?.toMutableList()

                        if (it.data != null) pageStateObserver.postValue(PageStates.SUCCESS)
                        else pageStateObserver.postValue(PageStates.NULL_DATA)
                    }
                    Resource.Status.LOADING -> {
                        pageStateObserver.postValue(PageStates.LOADING)
                    }
                    Resource.Status.ERROR -> {
                        pageStateObserver.postValue(PageStates.ERROR)
                    }
                }
            }
        }

    fun getNewPage() = viewModelScope.launch {
        if (pageable()) {
            currentPage += 1
            tvRepository.getPopularTvShows(GetPopularTvShowsRequest(page = currentPage)).collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        it.data?.results?.let {
                            tvShows?.addAll(it)
                        }
                        pageStateObserver.postValue(PageStates.NEXT_PAGE)
                    }
                    Resource.Status.LOADING -> {
                        pageStateObserver.postValue(PageStates.NEXT_PAGE_LOADING)
                    }
                    Resource.Status.ERROR -> {
                        pageStateObserver.postValue(PageStates.NEXT_PAGE_ERROR)
                    }
                }
            }
        }
    }

    private fun pageable() = currentPage < totalPage ?: 0

    override fun onCleared() {
        super.onCleared()
        currentPage = 1
    }
}