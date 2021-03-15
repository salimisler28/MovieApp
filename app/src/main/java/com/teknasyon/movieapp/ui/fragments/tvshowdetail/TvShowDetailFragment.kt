package com.teknasyon.movieapp.ui.fragments.tvshowdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.teknasyon.movieapp.R
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowDetailRequest
import com.teknasyon.movieapp.app.util.toast
import com.teknasyon.movieapp.databinding.FragmentTvShowDetailBinding
import com.teknasyon.movieapp.ui.activies.MainActivity
import com.teknasyon.movieapp.ui.util.PageStates
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvShowDetailFragment : Fragment(R.layout.fragment_tv_show_detail) {
    private val viewModel: TvShowDetailViewModel by viewModels()
    private var binding: FragmentTvShowDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            getString("TV-SHOW-HEADER")?.let {
                viewModel.header = it
            }

            getInt("TV-SHOW-ID")?.let {
                viewModel.id = it
                viewModel.getDetail(
                    GetPopularTvShowDetailRequest(
                        id = viewModel.id!!
                    )
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTvShowDetailBinding.bind(view)
        viewModel.id.toString().toast(requireContext())
        (requireActivity() as MainActivity).supportActionBar?.title = viewModel.header


        viewModel.tvShowPageStateObserver.observe(viewLifecycleOwner) {
            when (it) {
                PageStates.SUCCESS -> {
                    hideLoading()
                    binding?.txDetailOverview?.text = viewModel.tvShowDetailResponse?.data?.overview
                    binding?.txDetailCountry?.text =
                        viewModel.tvShowDetailResponse?.data?.origin_country.toString()
                            .replace("[", "")
                            .replace("]", "")
                }
                PageStates.LOADING -> {
                    showLoading()
                }
                PageStates.ERROR -> {
                    hideLoading()
                }
                else -> {
                }
            }
        }
    }

    private fun showLoading() {
        binding?.llDetail?.visibility = View.GONE
        binding?.pbDetail?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.llDetail?.visibility = View.VISIBLE
        binding?.pbDetail?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}