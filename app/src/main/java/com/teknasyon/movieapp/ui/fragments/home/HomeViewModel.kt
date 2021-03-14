package com.teknasyon.movieapp.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.movieapp.app.base.Resource
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
    val popularTvShowsPageStateObserver = MutableLiveData<PageStates>()
    var getPopularTvShowsResponse: GetPopularTvShowsResponse? = null

    init {
        popularTvShowsPageStateObserver.postValue(PageStates.IDLE)

        getPopularTvShows(
            GetPopularTvShowsRequest(
                page = 1
            )
        )
    }

    fun getPopularTvShows(getPopularTvShowsRequest: GetPopularTvShowsRequest) =
        viewModelScope.launch {
            tvRepository.getPopularTvShows(
                getPopularTvShowsRequest
            ).collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        getPopularTvShowsResponse = it.data
                        if (it.data?.results.isNullOrEmpty()) {
                            popularTvShowsPageStateObserver.postValue(PageStates.NULL_DATA)
                        } else {
                            popularTvShowsPageStateObserver.postValue(PageStates.SUCCESS)
                        }
                    }
                    Resource.Status.LOADING -> popularTvShowsPageStateObserver.postValue(PageStates.LOADING)
                    Resource.Status.ERROR -> {
                        popularTvShowsPageStateObserver.postValue(PageStates.ERROR)
                    }
                }
            }
        }
}