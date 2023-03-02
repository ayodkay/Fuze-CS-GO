package com.example.fuze.ui.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fuze.databinding.FragmentMatchBinding
import com.example.fuze.ui.BaseFragment


class MatchFragment : BaseFragment() {

    private lateinit var binding: FragmentMatchBinding
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentMatchBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = matchViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.event.observe(viewLifecycleOwner) {
            if (it) {
                matchViewModel.matchResponse.get()?.let {
                    navigateTo(MatchFragmentDirections.actionMatchFragmentToMatchDetails(it))
                }
            }
        }

        matchViewModel.swipeEvent.observe(viewLifecycleOwner) {
            if (it) {
                binding.swipe.isRefreshing = false
            }
        }

        binding.swipe.setOnRefreshListener {
            matchViewModel.getMatches()
        }
    }
}