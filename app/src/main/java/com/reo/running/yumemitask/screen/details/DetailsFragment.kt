package com.reo.running.yumemitask.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.databinding.DetailsviewItemRecyclerviewBinding
import com.reo.running.yumemitask.databinding.FragmentDetailsBinding
import com.reo.running.yumemitask.model.Github
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsFragment : Fragment() {
    private val readDao = YumemiApplication.db.contributorsDao()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var detailsList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            lifecycleScope.launch(Dispatchers.IO) {
                readDao.getAll().lastOrNull().let {
                    detailsList = listOf(

                        "id : ${it?.id.toString()}",
                        "node_id : ${it?.node_id.toString()}",
                        "avatar_url : ${it?.avatar_url.toString()}",
                        "gravatar_url : ${it?.gravatar_id.toString()}",
                        "url : ${it?.url.toString()}",
                        "html_url : ${it?.html_url.toString()}",
                        "followers_url : ${it?.followers_url.toString()}",
                        "following_url : ${it?.following_url.toString()}",
                        "gists_url : ${it?.gists_url.toString()}",
                        "starred_url : ${it?.starred_url.toString()}",
                        "subscriptions_url : ${it?.subscriptions_url.toString()}",
                        "organizations_url : ${it?.organizations_url.toString()}",
                        "repos_url : ${it?.repos_url.toString()}",
                        "events_url : ${it?.events_url.toString()}",
                        "received_events_url : ${it?.received_events_url.toString()}",
                        "type : ${it?.type.toString()}",
                        "site_admin : ${it?.site_admin.toString()}",
                        "contributions : ${it?.contributions.toString()}",

                        )
                }
                withContext(Dispatchers.Main) {
                    contributorsName.text = args.contributorsName
                    detailsRecyclerView.adapter = DetailsViewAdapter(detailsList)
                    detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    slideAnimation(detailsConstraintLayout)
                }
            }
        }
    }


    fun slideAnimation(view: View) {
        val translateAnimation = TranslateAnimation(
            1000f,
            0f,
            0f,
            0f,
        )
        translateAnimation.duration = 300
        view.startAnimation(translateAnimation)
    }

}