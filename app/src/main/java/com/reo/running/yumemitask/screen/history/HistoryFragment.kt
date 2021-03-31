package com.reo.running.yumemitask.screen.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.databinding.FragmentHistoryBinding
import com.reo.running.yumemitask.databinding.HistoryItemRecyclerviewBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by activityViewModels {
        HistoryViewModel.Companion.Factory()
    }
    private val historyRecyclerViewAdapter: HistoryViewAdapter by lazy {
        HistoryViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            historyRecyclerView.run {
                adapter = historyRecyclerViewAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        historyViewModel.contributorsList.observe(viewLifecycleOwner, {
            historyRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private inner class HistoryViewAdapter : RecyclerView.Adapter<HistoryViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
            HistoryViewHolder(
                HistoryItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            holder.binding.run {
                lifecycleOwner = viewLifecycleOwner
                historyViewModel.contributorsList.value?.get(position).let {
                    contributors = it
                    container.setOnClickListener {
                        historyViewModel.selectContributor(position)
                        val action =
                            HistoryFragmentDirections.actionNavHistoryToHistoryDetailsFragment(
                                position
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }

        override fun getItemCount(): Int = historyViewModel.contributorsList.value?.size ?: 0
    }

    private inner class HistoryViewHolder(val binding: HistoryItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
