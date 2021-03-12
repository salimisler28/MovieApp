package com.teknasyon.movieapp.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.teknasyon.movieapp.app.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {
}