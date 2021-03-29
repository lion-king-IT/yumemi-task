package com.reo.running.yumemitask.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.FragmentDetailsBinding
import com.reo.running.yumemitask.screen.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
            lifecycleScope.launch(Dispatchers.IO) {

            }
        }
    }


    fun slideAnimation(view: View) {
        val translateAnimation = TranslateAnimation(
            1000f,
            0f,
            0f,
            0f,
        )
        translateAnimation.duration = 300
        view.startAnimation(translateAnimation)
    }

}