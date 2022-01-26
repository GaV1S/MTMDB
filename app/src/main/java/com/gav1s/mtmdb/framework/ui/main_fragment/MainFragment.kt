package com.gav1s.mtmdb.framework.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gav1s.mtmdb.R
import com.gav1s.mtmdb.databinding.FragmentMainBinding
import com.gav1s.mtmdb.framework.ui.adapters.MainFragmentAdapterNew
import com.gav1s.mtmdb.framework.ui.adapters.MainFragmentAdapterTop
import com.gav1s.mtmdb.framework.ui.details_fragment.DetailsFragment
import com.gav1s.mtmdb.model.AppState
import com.gav1s.mtmdb.model.entities.Movie

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    private var adapterNew: MainFragmentAdapterNew? = null
    private var adapterTop: MainFragmentAdapterTop? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerViewNew.adapter = adapterNew
        binding.mainFragmentRecyclerViewTop.adapter = adapterTop
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                adapterNew = MainFragmentAdapterNew(object : OnItemViewClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val fragmentManager = activity?.supportFragmentManager
                        fragmentManager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setMovies(appState.moviesNewData)
                }
                adapterTop = MainFragmentAdapterTop(object : OnItemViewClickListener {
                    override fun onItemViewClick(movie: Movie) {
                        val fragmentManager = activity?.supportFragmentManager
                        fragmentManager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setMovies(appState.moviesTopData)
                }
                mainFragmentRecyclerViewNew.adapter = adapterNew
                mainFragmentRecyclerViewTop.adapter = adapterTop
            }
            is AppState.Loading -> {
                mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                appState.error.localizedMessage?.let {
                    mainFragmentLayout.showErrorSnackBar(
                        it,
                        getString(R.string.reload))
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun View.showErrorSnackBar(
        text: String,
        actionText: String,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length)
            .setAction(actionText) { viewModel.getData() }
            .show()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }
}