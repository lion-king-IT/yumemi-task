package com.reo.running.yumemitask.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R
import com.reo.running.yumemitask.databinding.FragmentListBinding
import com.reo.running.yumemitask.databinding.ItemRecyclerviewBinding
import com.reo.running.yumemitask.model.Github

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels {
        ListViewModel.Companion.Facroty()
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
        binding.vm = listViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            recyclerview.let {
                val adapter = ListViewAdapter()
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
                adapter.setOnItemClickListener(
                    object : ListFragment.OnClickListener {
                        override fun onItemClick(list: List<Int>, position: Int) {
                            slideAnimation(listConstraintLayout)
                            findNavController().navigate(R.id.action_nav_list_to_nav_details)
                        }
                    }
                )
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

        lateinit var listener: AdapterView.OnItemClickListener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
            ListViewHolder(
                ItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.binding.run {
                github = listViewModel.repositoryList.value?.get(position)
                lifecycleOwner = this@ListFragment
            }
        }

        override fun getItemCount(): Int = listViewModel.repositoryList.value?.size ?: 0

        fun setOnItemClickListener(listener: OnClickListener) {
            this.listener = listener
        }

    }

    interface OnClickListener : AdapterView.OnItemClickListener {
        fun onItemClick(list: List<Int>, position: Int)
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
    }


    private inner class ListViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}