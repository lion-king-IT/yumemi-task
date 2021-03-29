package com.reo.running.yumemitask.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    private val detailsViewModel: DetailsViewModel by activityViewModels()
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
                readDao.getAll().let {
                    it[args.index].run {
                        detailsList = listOf(
                            "id : $id",
                            "node_id : $node_id",
                            "avatar_url : $avatar_url",
                            "gravatar_url : $gravatar_id",
                            "url : $url",
                            "html_url : $html_url",
                            "followers_url : $followers_url",
                            "following_url : $following_url",
                            "gists_url : $gists_url",
                            "starred_url : $starred_url",
                            "subscriptions_url : $subscriptions_url",
                            "organizations_url : $organizations_url",
                            "repos_url : $repos_url",
                            "events_url : $events_url",
                            "received_events_url : $received_events_url",
                            "type : $type",
                            "site_admin : $site_admin",
                            "contributions : $contributions",
                        )
                    }

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