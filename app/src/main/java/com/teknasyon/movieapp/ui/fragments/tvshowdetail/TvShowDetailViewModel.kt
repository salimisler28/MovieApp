package com.teknasyon.movieapp.ui.fragments.tvshowdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.movieapp.app.base.Resource
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowDetailRequest
import com.teknasyon.movieapp.app.network.response.TvShowDetailResponse
import com.teknasyon.movieapp.app.repository.TvRepository
import com.teknasyon.movieapp.ui.util.PageStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {
    var header: String? = null
    var id: Int? = null

    var tvShowDetailResponse: Resource<TvShowDetailResponse>? = null
    var tvShowPageStateObserver = MutableLiveData<PageStates>()

    fun getDetail(request: GetPopularTvShowDetailRequest) = viewModelScope.launch {
        tvRepository.getTvShowDetail(request)
            .collect {
                tvShowDetailResponse = it

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data != null) {
                            tvShowPageStateObserver.postValue(PageStates.SUCCESS)
                        } else {
                            tvShowPageStateObserver.postValue(PageStates.NULL_DATA)
                        }
                    }
                    Resource.Status.LOADING -> tvShowPageStateObserver.postValue(PageStates.LOADING)
                    Resource.Status.ERROR -> {
                        tvShowPageStateObserver.postValue(PageStates.ERROR)
                    }
                }
            }
    }
}