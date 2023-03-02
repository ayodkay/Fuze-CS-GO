package com.example.fuze.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fuze.databinding.FragmentMatchDetailsBinding
import com.example.fuze.ui.BaseFragment


class MatchDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentMatchDetailsBinding
    private val matchDetailsViewModel: MatchDetailsViewModel by viewModels()
    private val args: MatchDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentMatchDetailsBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = matchDetailsViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchDetailsViewModel.matchResponse.set(args.matchResponse)
        matchDetailsViewModel.setValues()
        matchDetailsViewModel.getPlayer()

        onBackPressed { navigateTo(MatchDetailsFragmentDirections.actionMatchDetailsToMatchFragment()) }

        matchDetailsViewModel.event.observe(viewLifecycleOwner) {
            if (it) {
                navigateTo(MatchDetailsFragmentDirections.actionMatchDetailsToMatchFragment())
            }
        }
    }
}