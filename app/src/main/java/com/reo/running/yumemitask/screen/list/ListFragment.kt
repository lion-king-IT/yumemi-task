package com.reo.running.yumemitask.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.databinding.FragmentListBinding
import com.reo.running.yumemitask.databinding.ItemRecyclerviewBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels {
        ListViewModel.Companion.Facroty()
    }
    private val listRecyclerViewAdapter: ListViewAdapter by lazy {
        ListViewAdapter()
    }
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.vm = listViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            recyclerview.apply {
                adapter = listRecyclerViewAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            listViewModel.repositoryList.observe(viewLifecycleOwner) {
                listRecyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    fun slideAnimation(view: View) {
        val translateAnimation = TranslateAnimation(
            0f,
            -1000f,
            0f,
            0f,
        )
        translateAnimation.run {
            duration = 200
            fillAfter = true
        }
        view.startAnimation(translateAnimation)
    }

    private inner class ListViewAdapter : RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder = ListViewHolder(
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(requireContext()),parent,false))

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.binding.run {
                github = listViewModel.repositoryList.value?.get(position)
                lifecycleOwner = this@ListFragment
            }
        }

        override fun getItemCount(): Int = listViewModel.repositoryList.value?.size ?: 0
    }

    private inner class ListViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}