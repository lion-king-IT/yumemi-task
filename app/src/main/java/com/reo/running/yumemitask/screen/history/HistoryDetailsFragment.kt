package com.reo.running.yumemitask.screen.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reo.running.yumemitask.databinding.FragmentHistoryDetailsBinding

class HistoryDetailsFragment: Fragment() {
    private lateinit var binding: FragmentHistoryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryDetailsBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}