package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presenter.auth.AuthPresenter
import com.example.taximuslim.utils.NumberValidator
import kotlinx.android.synthetic.main.fragment_enter_number.*

class EnterNumberFragment : BaseAuthFragment() {
    companion object {
        const val FRAGMENT_ID = "ENTER_NUMBER_FRAGMENT"
        val INSTANCE =
            EnterNumberFragment()

        const val COUNTRY_CODE = "+7"
    }

    override fun layoutId() = R.layout.fragment_enter_number

    override fun initViews() {
        continue_btn.setOnClickListener(this)
    }

    private val presenter = AuthPresenter()

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.continue_btn -> {
                if (NumberValidator.isValidNumber(enter_number.text.toString())) {
                    saveNumber()
                    (activity as AuthController)
                        .replaceFragment(
                            EnterSmsCodeFragment.INSTANCE,
                            R.id.container,
                            EnterSmsCodeFragment.FRAGMENT_ID
                        )
                } else showToast(getString(R.string.number_exeption))
            }
        }
    }

    private fun saveNumber() =
        presenter.saveNumber(COUNTRY_CODE + enter_number.text.toString())



}