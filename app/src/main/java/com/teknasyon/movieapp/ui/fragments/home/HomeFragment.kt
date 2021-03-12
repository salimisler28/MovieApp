package com.teknasyon.movieapp.ui.fragments.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.teknasyon.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
}