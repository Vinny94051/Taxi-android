package com.example.taximuslim.presentation.view.menu.fragments

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment

class SettingsFragment : BaseFragment() {
    companion object {
        const val FRAGMENT_ID = "SETTINGS_FRAGMENT"
        val INSTANCE = SettingsFragment()
    }

    override fun layoutId() = R.layout.fragment_settings
}