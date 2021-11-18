package com.koshake1.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.koshake1.movieapp.R
import com.koshake1.movieapp.databinding.FragmentOverviewBinding
import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import com.koshake1.movieapp.model.repository.image.ImageLoader
import com.koshake1.movieapp.util.POSTER_BASE_URL
import com.koshake1.movieapp.view_model.OverviewViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : Fragment(R.layout.fragment_overview) {

    private var bindingNullable: FragmentOverviewBinding? = null
    private val binding: FragmentOverviewBinding
        get() = bindingNullable!!

    private val overviewViewModel: OverviewViewModel by viewModel()

    private val imageLoader: ImageLoader<ImageView> by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("key")
        id?.let { overviewViewModel.getMovie(it) }
        overviewViewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun renderData(state: MoviesViewState<Movie>) {
        when (state) {
            is MoviesViewState.Success -> renderSuccess(state.data)
            is MoviesViewState.Error -> renderError(state.error)
            is MoviesViewState.Loading -> setLoading(true)
        }
    }

    private fun renderSuccess(movie: Movie) {
        hideLoading()
        binding.textViewTitle.text = movie.original_title
        binding.textviewOverviewText.text = movie.overview
        val url = POSTER_BASE_URL + movie.poster_path
        imageLoader.loadImage(binding.imageTitle, url)
    }

    private fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
    }

    private fun showLoading() {
        binding.overviewProgress.show()
    }

    private fun hideLoading() {
        if (binding.overviewProgress.isShown) {
            binding.overviewProgress.hide()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            showLoading()
        } else {
            hideLoading()
        }
    }
}