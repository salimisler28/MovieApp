package com.teknasyon.movieapp.ui.fragments.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teknasyon.movieapp.R
import com.teknasyon.movieapp.app.network.dto.TvShowDto
import com.teknasyon.movieapp.app.util.toast
import com.teknasyon.movieapp.databinding.FragmentHomeBinding
import com.teknasyon.movieapp.ui.fragments.home.adapter.HomeController
import com.teknasyon.movieapp.ui.util.GridSpacingItemDecoration
import com.teknasyon.movieapp.ui.util.PageStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private var controller: HomeController? = null
    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEpoxyController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setEpoxyAdapter()
        setListeners()

        viewModel.pageStateObserver.observe(viewLifecycleOwner) {
            homeFragment(it)
        }
    }

    private fun setListeners() {
        controller?.onTvShowItemClickListener = {
            it?.id?.let { _id ->
                val bundle = Bundle().apply {
                    putInt("TV-SHOW-ID", _id)
                    putString("TV-SHOW-HEADER", it.name)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_tvShowDetailFragment,
                    bundle
                )
            }
        }

        controller?.newPageListener = {
            // Paging when users see 10 minus of all items check TvShowItem.kt
            viewModel.getNewPage()
        }
    }

    private fun homeFragment(state: PageStates?) {
        controller?.state = state
        controller?.data = viewModel.tvShows
        request()
    }

    private fun setEpoxyController() {
        this.controller = HomeController()
    }

    private fun setEpoxyAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.spanSizeLookup = controller?.spanSizeLookup
        binding?.ervHome?.layoutManager = gridLayoutManager
        binding?.ervHome?.adapter = controller?.adapter
        binding?.ervHome?.addItemDecoration(GridSpacingItemDecoration(2, 30, true))
    }

    private fun request() {
        controller?.requestModelBuild()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}