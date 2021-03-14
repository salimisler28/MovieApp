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

        viewModel.popularTvShowsPageStateObserver.observe(viewLifecycleOwner) {
            homeFragment(it)
        }
    }

    private fun setListeners() {
        controller?.onTvShowItemClickListener = {
            it?.id?.let { _id ->
                val bundle = Bundle().apply {
                    putInt("TV-SHOW-ID", _id)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_tvShowDetailFragment,
                    bundle
                )
            }
        }
    }

    private fun homeFragment(state: PageStates?) {
        setState(state)
        setData(viewModel.getPopularTvShowsResponse?.results)
        request()
    }

    private fun setEpoxyController() {
        this.controller = HomeController()
    }

    private fun setEpoxyAdapter() {
        binding?.ervHome?.layoutManager = GridLayoutManager(requireContext(), 2)
        binding?.ervHome?.adapter = controller?.adapter
        binding?.ervHome?.addItemDecoration(GridSpacingItemDecoration(2, 30, true))
    }

    private fun setData(data: List<TvShowDto>?) {
        controller?.data = data
    }

    private fun setState(state: PageStates?) {
        controller?.state = state
    }

    private fun request() {
        controller?.requestModelBuild()
    }
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left =
                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}