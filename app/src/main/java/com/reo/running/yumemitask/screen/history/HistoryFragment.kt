package com.reo.running.yumemitask.screen.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.FragmentHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {
    private val contributorsDao = YumemiApplication.db.contributorsDao()
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var action: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            lifecycleScope.launch(Dispatchers.IO) {
                val lastIndex = contributorsDao.getAll().lastIndex
                val historyList: MutableList<String> = mutableListOf()
                contributorsDao.getAll().let {
                    for (i in lastIndex downTo 0 step 1) {
                        historyList.add(it[i].login)
                    }
                }
                withContext(Dispatchers.Main) {
                    historyRecyclerView.adapter = HistoryViewAdapter(historyList)
                    historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                }

            }


        }
    }

}
