package com.example.taximuslim.presentation.view.menu.fragments

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment

class GuideFragment : BaseFragment() {
    companion object {
        const val FRAGMENT_ID = "GUIDE_FRAGMENT"
        val INSTANCE = GuideFragment()
    }

    override fun layoutId() = R.layout.fragment_guide
}