package com.example.taximuslim.presentation.view.menu.fragments

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment

class HistoryFragment : BaseFragment() {
    companion object {
        const val FRAGMENT_ID = "HISTORY_FRAGMENT"
        val INSTANCE = HistoryFragment()
    }

    override fun layoutId() = R.layout.fragment_history
}