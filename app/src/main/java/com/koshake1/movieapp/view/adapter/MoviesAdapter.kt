package com.koshake1.movieapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.movieapp.R
import com.koshake1.movieapp.databinding.ItemMovieBinding
import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.repository.image.GlideImageLoader
import com.koshake1.movieapp.model.repository.image.ImageLoader
import com.koshake1.movieapp.util.POSTER_BASE_URL

class MoviesAdapter(var items: List<Movie> = ArrayList(),
                    val imageLoader: ImageLoader<ImageView>,
                    val clickListener: OnListItemClickListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rvBinding = ItemMovieBinding.bind(itemView)
        fun bind(currentItem : Movie) {
            with (rvBinding) {
                textViewMovieName.text = currentItem.original_title
                textViewDate.text = currentItem.release_date
                textviewGrade.text = currentItem.vote_average.toString()

                val url = POSTER_BASE_URL + currentItem.poster_path
                imageLoader.loadImage(image, url)

                image.setOnClickListener { clickListener.onItemClick(currentItem.id!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie,
            parent, false
        )
        return MoviesViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun fillList(items: List<Movie>) {
        this.items += items
        notifyDataSetChanged()
    }

    fun clear() {
        this.items = emptyList()
    }
}