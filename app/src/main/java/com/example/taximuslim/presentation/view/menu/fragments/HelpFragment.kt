package com.example.taximuslim.presentation.view.menu.fragments

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : BaseAuthFragment() {
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.sent_letter_button -> {}
            R.id.enter_button ->{}
        }
    }

    override fun initViews() {
        sent_letter_button.setOnClickListener(this)
        enter_button.setOnClickListener(this)
    }

    companion object {
        const val FRAGMENT_ID = "HELP_FRAGMENT"
        val INSTANCE = HelpFragment()
    }
    override fun layoutId() = R.layout.fragment_help
}