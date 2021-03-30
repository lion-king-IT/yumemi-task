package com.reo.running.yumemitask.screen.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.databinding.FragmentListBinding
import com.reo.running.yumemitask.databinding.ListviewItemRecyclerviewBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by activityViewModels() {
        ListViewModel.Companion.Factory()
    }
    private val listRecyclerViewAdapter: ListViewAdapter by lazy {
        ListViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            recyclerview.run {
                adapter = listRecyclerViewAdapter
                layoutManager =
                    LinearLayoutManager(requireContext())
            }
        }
        listViewModel.contributorsList.observe(viewLifecycleOwner, Observer {
            listRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private inner class ListViewAdapter : RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
            ListViewHolder(
                ListviewItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.binding.run {
                lifecycleOwner = viewLifecycleOwner
                listViewModel.contributorsList.value?.get(position)?.let {
                    github = it
                    container.setOnClickListener { _ ->
                        listViewModel.selectContributor(position)
                        ListFragmentDirections.actionNavListToNavDetails(
                            it.login
                        ).run {
                            findNavController().navigate(this)
                        }
                    }
                }
            }
        }

        override fun getItemCount(): Int = listViewModel.contributorsList.value?.size ?: 0

    }

    private inner class ListViewHolder(val binding: ListviewItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

}