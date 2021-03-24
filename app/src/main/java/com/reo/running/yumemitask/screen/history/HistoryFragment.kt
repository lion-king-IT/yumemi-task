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
                contributorsDao.getAll().let {
                    lastIndex = it.lastIndex
                    println(lastIndex)
                    withContext(Dispatchers.Main) {
                        when {
                            lastIndex > 3 -> {
                                firstHistory.text = it[lastIndex].login
                                firstHistory.visibility = View.VISIBLE
                                secondHistory.text = it[lastIndex - 1].login
                                secondHistory.visibility = View.VISIBLE
                                thirdHistory.text = it[lastIndex - 2].login
                                thirdHistory.visibility = View.VISIBLE
                                fourthHistroy.text = it[lastIndex - 3].login
                                fourthHistroy.visibility = View.VISIBLE
                                fifthHistory.text = it[lastIndex - 4].login
                                fifthHistory.visibility = View.VISIBLE
                            }
                            lastIndex > 2 -> {
                                firstHistory.text = it[lastIndex].login
                                firstHistory.visibility = View.VISIBLE
                                secondHistory.text = it[lastIndex - 1].login
                                secondHistory.visibility = View.VISIBLE
                                thirdHistory.text = it[lastIndex - 2].login
                                thirdHistory.visibility = View.VISIBLE
                                fourthHistroy.text = it[lastIndex - 3].login
                                fourthHistroy.visibility = View.VISIBLE
                            }
                            lastIndex > 1 -> {
                                firstHistory.text = it[lastIndex].login
                                firstHistory.visibility = View.VISIBLE
                                secondHistory.text = it[lastIndex - 1].login
                                secondHistory.visibility = View.VISIBLE
                                thirdHistory.text = it[lastIndex - 2].login
                                thirdHistory.visibility = View.VISIBLE
                            }

                            lastIndex > 0 -> {
                                firstHistory.text = it[lastIndex].login
                                firstHistory.visibility = View.VISIBLE
                                secondHistory.text = it[lastIndex - 1].login
                                secondHistory.visibility = View.VISIBLE
                            }

                            lastIndex > -1 -> {
                                firstHistory.text = it[lastIndex].login
                                firstHistory.visibility = View.VISIBLE
                            }

                            else -> {
                                firstHistory.text = "なし"
                            }
                        }
                    }
                }
            }

            firstHistory.setOnClickListener {
                action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    firstHistory.text.toString(),
                    lastIndex
                )
                findNavController().navigate(action)
            }
            secondHistory.setOnClickListener {
                action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    secondHistory.text.toString(),
                    (lastIndex - 1)
                )
                findNavController().navigate(action)
            }
            thirdHistory.setOnClickListener {
                action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    thirdHistory.text.toString(),
                    (lastIndex - 2)
                )
                findNavController().navigate(action)
            }
            fourthHistroy.setOnClickListener {
                action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    fourthHistroy.text.toString(),
                    (lastIndex - 3)
                )
                findNavController().navigate(action)
            }
            fifthHistory.setOnClickListener {
                action = HistoryFragmentDirections.actionNavHistoryToNavDetails(
                    fifthHistory.text.toString(),
                    (lastIndex - 4)
                )
                findNavController().navigate(action)

            }
        }
    }

}
