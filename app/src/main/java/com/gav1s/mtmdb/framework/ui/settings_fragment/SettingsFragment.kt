package com.gav1s.mtmdb.framework.ui.settings_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gav1s.mtmdb.databinding.FragmentSettingsBinding
import com.gav1s.mtmdb.framework.AppSettings

class SettingsFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderData()
    }

    private fun renderData() = with(binding) {
        adultCheck.isChecked = AppSettings.adultShow
        adultCheck.setOnClickListener {
            AppSettings.adultShow = adultCheck.isChecked
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}