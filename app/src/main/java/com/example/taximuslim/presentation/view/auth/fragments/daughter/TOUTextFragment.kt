package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_terms_of_use_text.*

class TOUTextFragment : BaseAuthFragment() {
    override fun layoutId() = R.layout.fragment_terms_of_use_text

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_button_back -> (activity as AuthController).replaceFragment(
                TOUAgreeFragment.INSTANCE, R.id.container, TOUAgreeFragment.FRAGMENT_ID
            )
        }
    }

    override fun initViews() {
        main_button_back.setOnClickListener(this)
    }

    companion object {
        const val FRAGMENT_ID = "TERMS_OF_USE_TEXT_FRAGMENT"
        val INSTANCE = TOUTextFragment()
    }
}