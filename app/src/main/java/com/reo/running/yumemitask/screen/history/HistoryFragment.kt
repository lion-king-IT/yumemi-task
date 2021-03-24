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
import androidx.navigation.fragment.findNavController
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.FragmentHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {
    private val contributorsDao = YumemiApplication.db.contributorsDao()
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels()
    private var lastIndex = 0

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
                contributorsDao.getAll().let {
                    withContext(Dispatchers.Main) {
                        lastIndex = it.lastIndex
                        println(lastIndex)
                        when {
                            lastIndex > 4 -> {
                                firstHistory.text = it[lastIndex].login
                                secondHistory.text = it[lastIndex - 1].login
                                thirdHistory.text = it[lastIndex - 2].login
                                fourthHistroy.text = it[lastIndex - 3].login
                                fifthHistory.text = it[lastIndex - 4].login
                            }
                            lastIndex > 3 -> {
                                firstHistory.text = it[lastIndex].login
                                secondHistory.text = it[lastIndex - 1].login
                                thirdHistory.text = it[lastIndex - 2].login
                                fourthHistroy.text = it[lastIndex - 3].login
                            }
                            lastIndex > 2 -> {
                                firstHistory.text = it[lastIndex].login
                                secondHistory.text = it[lastIndex - 1].login
                                thirdHistory.text = it[lastIndex - 2].login
                            }

                            lastIndex > 1 -> {
                                firstHistory.text = it[lastIndex].login
                                secondHistory.text = it[lastIndex - 1].login
                            }

                            lastIndex > 0 -> {
                                firstHistory.text = it[lastIndex].login
                            }

                            else -> {
                                firstHistory.text = "なし"
                            }
                        }
                    }
                }
            }

            firstHistory.setOnClickListener {
                val action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex
                )
                findNavController().navigate(action)
            }
            secondHistory.setOnClickListener {
                val action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex - 1
                )
                findNavController().navigate(action)
            }
            thirdHistory.setOnClickListener {
                val action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex - 2
                )
                findNavController().navigate(action)
            }
            fourthHistroy.setOnClickListener {
                val action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex - 3
                )
                findNavController().navigate(action)
            }
            fifthHistory.setOnClickListener {
                val action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex - 4
                )
                findNavController().navigate(action)
            }
        }
    }

}
