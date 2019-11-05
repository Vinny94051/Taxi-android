package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment : BaseAuthFragment() {

    companion object {
        const val FRAGMENT_ID = "AUTH_FRAGMENT"
        val INSTANCE = AuthorizationFragment()
    }

    override fun layoutId() = R.layout.fragment_authorization

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.enter_button ->{}
        }
    }

    override fun initViews() {
        enter_button.setOnClickListener(this)
    }
}