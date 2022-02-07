package com.gav1s.mtmdb.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gav1s.mtmdb.databinding.FragmentMainRecyclerItemBinding
import com.gav1s.mtmdb.framework.ui.main_fragment.MainFragment
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.repository.BASE_IMAGE_URL

class MainFragmentAdapter(private var itemClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var moviesData: List<Movie> = listOf()

    fun setMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount() = moviesData.size

    inner class MainViewHolder(private val binding: FragmentMainRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            moviePoster.load(BASE_IMAGE_URL + movie.poster_path)
            movieTitle.text = movie.title
            movieDate.text = movie.release_date
            root.setOnClickListener { itemClickListener?.onItemViewClick(movie) }
        }
    }
}