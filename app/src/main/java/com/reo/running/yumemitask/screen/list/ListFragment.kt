package com.reo.running.yumemitask.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R
import com.reo.running.yumemitask.databinding.FragmentListBinding
import com.reo.running.yumemitask.databinding.ItemRecyclerviewBinding
import com.reo.running.yumemitask.screen.list.recyclerview.ListViewAdapter

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels()
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
            val detailsOfContributor = listOf<String>(
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
                "ここにContributorの名前を表示させる",
            )
            val adapter = ListViewAdapter(detailsOfContributor,position)
            adapter.setOnItemClickListener(
                object : ListViewAdapter.OnItemClickListener {
                    override fun onItemClick(list: List<String>, position: Int) {
                        findNavController().navigate(R.id.action_nav_list_to_nav_details)
                    }
                }
            )
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}