package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.utils.NumberValidator
import kotlinx.android.synthetic.main.fragment_enter_number.*

class EnterNumberFragment : BaseAuthFragment() {
    override fun initViews() {
        continue_btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.continue_btn -> {
                if (NumberValidator.isValidNumber(enter_number.text.toString()))
                    (activity as AuthController)
                        .replaceFragment(
                            EnterSmsCodeFragment.INSTANCE,
                            R.id.container,
                            EnterSmsCodeFragment.FRAGMENT_ID
                        )
                else {
                    showToast(getString(R.string.number_exeption))
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_ID = "ENTER_NUMBER_FRAGMENT"
        val INSTANCE =
            EnterNumberFragment()
    }

    override fun layoutId() = R.layout.fragment_enter_number



}