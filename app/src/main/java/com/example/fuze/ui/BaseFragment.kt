package com.example.fuze.ui

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

open class BaseFragment : Fragment() {

    protected fun onBackPressed(action: (() -> Unit)) {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            action.invoke()
        }
    }

    protected fun navigateTo(direction: NavDirections) {
        NavHostFragment.findNavController(this).navigate(direction)
    }
}