package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : BaseAuthFragment() {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_button_welcome -> {
                (activity as AuthActivity)
                    .addFragment(
                        EnterNumberFragment.INSTANCE, R.id.container,
                        EnterNumberFragment.FRAGMENT_ID
                    )
            }
        }
    }

    override fun initViews() = main_button_welcome.setOnClickListener(this)

    companion object {
        const val FRAGMENT_ID = "WELCOME_FRAGMENT"
        val INSTANCE =
            WelcomeFragment()
    }

    override fun layoutId() = R.layout.fragment_welcome
    override fun buttonText(): String = getString(R.string.start)

}