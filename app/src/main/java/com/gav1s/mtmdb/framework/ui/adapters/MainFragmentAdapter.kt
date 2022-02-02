package com.gav1s.mtmdb.framework.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gav1s.mtmdb.databinding.FragmentMainRecyclerItemBinding
import com.gav1s.mtmdb.framework.ui.main_fragment.MainFragment
import com.gav1s.mtmdb.model.entities.Movie

class MainFragmentAdapter(private var itemClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var moviesData: List<Movie> = listOf()
    private lateinit var binding: FragmentMainRecyclerItemBinding

    fun setMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int = moviesData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) = with(binding) {
            moviePoster.load("https://image.tmdb.org/t/p/original" + movie.poster_path)
            movieTitle.text = movie.title
            movieDate.text = movie.release_date
            root.setOnClickListener { itemClickListener?.onItemViewClick(movie) }
        }
    }
}