package com.example.taximuslim.presentation.view.mainscreen

import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class ChooseDriverFragment : BaseFragment() {

    companion object : BaseFragmentCompanion(){
        override val ID: String
            get() = "CHOOSE_FRAGMENT_COMPANION"

        override fun newInstance(): Fragment = ChooseDriverFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_choose_driver

    override fun buttonText(): String = getString(R.string.continuee)
}