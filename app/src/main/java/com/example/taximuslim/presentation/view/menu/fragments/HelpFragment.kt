package com.example.taximuslim.presentation.view.menu.fragments

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment

class HelpFragment : BaseFragment() {
    companion object {
        const val FRAGMENT_ID = "HELP_FRAGMENT"
        val INSTANCE = HelpFragment()
    }
    override fun layoutId() = R.layout.fragment_help
}