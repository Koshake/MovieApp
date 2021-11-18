package com.koshake1.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.koshake1.movieapp.R
import com.koshake1.movieapp.databinding.FragmentMoviesBinding
import com.koshake1.movieapp.model.data.MoviesResponse
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import com.koshake1.movieapp.model.repository.image.ImageLoader
import com.koshake1.movieapp.view.adapter.MoviesAdapter
import com.koshake1.movieapp.view.adapter.OnListItemClickListener
import com.koshake1.movieapp.view_model.MoviesViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var bindingNullable: FragmentMoviesBinding? = null

    private val binding get() = bindingNullable!!

    private val moviesViewModel: MoviesViewModel by viewModel()

    private val imageLoader: ImageLoader<ImageView> by inject()

    private var adapter: MoviesAdapter? = null

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

        initAdapter()

        moviesViewModel.getMovies()

        moviesViewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun initAdapter() {
        if (adapter == null) {
            adapter = MoviesAdapter(imageLoader = imageLoader, clickListener = object
                : OnListItemClickListener {
                override fun onItemClick(id: String) {
                    val bundle = Bundle()
                    bundle.putString("key", id)
                    findNavController().navigate(
                        R.id.action_moviesFragment_to_overviewFragment, bundle
                    )
                }
            })
        }
        binding.mainRecycler.adapter = adapter
    }

    private fun renderData(state: MoviesViewState<MoviesResponse>) {
        when (state) {
            is MoviesViewState.Success -> renderSuccess(state.data)
            is MoviesViewState.Error -> renderError(state.error)
            is MoviesViewState.Loading -> setLoading(true)
        }
    }

    private fun renderSuccess(movies: MoviesResponse) {
        hideLoading()
        adapter?.let {
            it.clear()
            it.fillList(movies.results)
        }
    }

    private fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
    }

    private fun setLoading(loading: Boolean) {
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