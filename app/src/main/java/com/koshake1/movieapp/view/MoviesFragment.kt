package com.koshake1.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.koshake1.movieapp.R
import com.koshake1.movieapp.databinding.FragmentMoviesBinding
import com.koshake1.movieapp.model.data.MoviesResponse
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import com.koshake1.movieapp.view_model.MoviesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private var bindingNullable : FragmentMoviesBinding? = null

    private val binding get() = bindingNullable!!

    private val moviesViewModel : MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.getMovies()

        moviesViewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun renderData(state : MoviesViewState<MoviesResponse>) {
        when (state) {
            is MoviesViewState.Success -> renderSuccess(state.data)
            is MoviesViewState.Error -> renderError(state.error)
            is MoviesViewState.Loading -> setLoading(true)
        }
    }

    private fun renderSuccess(movies : MoviesResponse) {

    }

    private fun renderError(error : Throwable) {
        error.message?.let { showMessage(it) }
    }

    private fun setLoading(loading : Boolean) {
        if (loading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun showLoading() {
        binding.progressBar.show()
    }

    private fun hideLoading() {
        if (binding.progressBar.isShown) {
            binding.progressBar.hide()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}