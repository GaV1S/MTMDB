package com.gav1s.mtmdb.framework.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gav1s.mtmdb.R
import com.gav1s.mtmdb.databinding.FragmentMainBinding
import com.gav1s.mtmdb.framework.AppSettings
import com.gav1s.mtmdb.framework.ui.adapters.MainFragmentAdapter
import com.gav1s.mtmdb.framework.ui.details_fragment.DetailsFragment
import com.gav1s.mtmdb.model.AppState
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.repository.RemoteDataSource
import com.gav1s.mtmdb.model.repository.RepositoryImpl
import com.gav1s.mtmdb.utils.showSnackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel {
        parametersOf(RepositoryImpl(RemoteDataSource()))
    }
    private var adapter: MainFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getNewDataFromServer()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading ->
                LoadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                LoadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val fragmentManager = activity?.supportFragmentManager
                        fragmentManager?.let { manager ->
                            val bundle = Bundle().apply {
                                putInt(DetailsFragment.BUNDLE_EXTRA, movie.id)
                            }
                            manager.beginTransaction()
                                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setMovies(if (AppSettings.adultShow) {
                        appState.moviesData
                    } else {
                        appState.moviesData.filter { !it.adult }
                    })
                }
                RecyclerView.adapter = adapter
            }
            is AppState.Error -> {
                LoadingLayout.visibility = View.GONE
                appState.error.localizedMessage?.let {
                    view?.showSnackBar(
                        it,
                        getString(R.string.reload),
                        { viewModel.getNewDataFromServer() }
                    )
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}