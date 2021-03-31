package com.reo.running.yumemitask.screen.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.reo.running.yumemitask.databinding.FragmentHistoryDetailsBinding

class HistoryDetailsFragment: Fragment() {
    private lateinit var binding: FragmentHistoryDetailsBinding
    private val historyViewModel: HistoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryDetailsBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = historyViewModel
        return binding.root

    }
}