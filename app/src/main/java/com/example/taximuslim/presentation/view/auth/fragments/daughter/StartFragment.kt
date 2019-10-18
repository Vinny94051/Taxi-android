package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : BaseAuthFragment() {
    override fun initViews() = main_button.setOnClickListener(this)
    override fun layoutId() = R.layout.fragment_start

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_button -> {
                (activity as AuthController)
                    .replaceFragment(
                        WelcomeFragment.INSTANCE, R.id.container,
                        WelcomeFragment.FRAGMENT_ID
                    )
            }
        }
    }

    companion object {
        const val FRAGMENT_ID = "START_FRAGMENT"
        val INSTANCE =
            StartFragment()
    }


}