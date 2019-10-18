package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment

class TOUTextFragment : BaseAuthFragment() {
    override fun layoutId() = R.layout.fragment_terms_of_use_text

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val FRAGMENT_ID = "TERMS_OF_USE_TEXT_FRAGMENT"
        val INSTANCE = TOUTextFragment()
    }
}