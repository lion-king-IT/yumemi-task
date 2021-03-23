package com.reo.running.yumemitask.screen.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.FragmentHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {
    private val readDao = YumemiApplication.db.contributorsDao()
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels()

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
                readDao.getAll().let {
                    val lastIndex = it.lastIndex
                    when {
                        lastIndex > 4 -> {
                            firstHistory.text = it[lastIndex].login
                            secondHistory.text = it[lastIndex - 1].login
                            thirdHistory.text = it[lastIndex - 2].login
                        }
                        lastIndex > 3 -> {
                            firstHistory.text = it[lastIndex].login
                            secondHistory.text = it[lastIndex - 1].login
                            thirdHistory.text = it[lastIndex - 2].login
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

                        lastIndex == null -> {
                            firstHistory.text = "なし"
                        }
                    }

                }

            }

        }
    }
}