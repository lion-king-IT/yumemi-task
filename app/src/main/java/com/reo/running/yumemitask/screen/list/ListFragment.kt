package com.reo.running.yumemitask.screen.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.FragmentListBinding
import com.reo.running.yumemitask.databinding.ListviewItemRecyclerviewBinding
import com.reo.running.yumemitask.model.Github
import com.reo.running.yumemitask.model.room.ContributorsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val contributorsDao = YumemiApplication.db.contributorsDao()
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
            recyclerview.run {
                adapter = listRecyclerViewAdapter
                layoutManager =
                    LinearLayoutManager(requireContext())
            }
        }
        listViewModel.repositoryList.observe(viewLifecycleOwner) {
            listRecyclerViewAdapter.notifyDataSetChanged()
        }
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
                lifecycleOwner = this@ListFragment
                github = listViewModel.repositoryList.value?.get(position)
                github.run {
                    nameContributors.setOnClickListener {
                        Log.d("debug","listViewModel.repositoryList.value?.get(position) = ${listViewModel.repositoryList.value?.get(position)}")
                        Log.d("debug","listViewModel.repository.value = ${listViewModel.repositoryList.value}")
                        Log.d("debug","listViewModel.repository = ${listViewModel.repositoryList}")
                        Log.d("debug","github = $github")
                        github?.run {
                            val contributorsData = ContributorsData(
                                0,
                                id,
                                login,
                                node_id,
                                avatar_url,
                                gravatar_id,
                                url,
                                html_url,
                                followers_url,
                                following_url,
                                gists_url,
                                starred_url,
                                subscriptions_url,
                                organizations_url,
                                repos_url,
                                events_url,
                                received_events_url,
                                type,
                                site_admin,
                                contributions
                            )
                            lifecycleScope.launch(Dispatchers.IO) {
                                contributorsDao.insertContributors(contributorsData)
                                Log.d("debug","contributorsDao.getAll() = ${contributorsDao.getAll()}")
                            }
                            val contributorsName = nameContributors.text.toString()
                            val action =
                                ListFragmentDirections.actionNavListToNavDetails(contributorsName)
                            findNavController().navigate(action)
                        }

                    }

                }
            }
        }

        override fun getItemCount(): Int = listViewModel.repositoryList.value?.size ?: 0


    }

    private inner class ListViewHolder(val binding: ListviewItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

}