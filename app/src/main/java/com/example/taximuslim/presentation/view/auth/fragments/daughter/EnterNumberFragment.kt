package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.content.Context
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.utils.NumberValidator
import kotlinx.android.synthetic.main.fragment_enter_number.*

class EnterNumberFragment : BaseAuthFragment() {
    companion object {
        const val FRAGMENT_ID = "ENTER_NUMBER_FRAGMENT"
        val INSTANCE =
            EnterNumberFragment()

    }

    private lateinit var owner: AuthActivity

    override fun layoutId() = R.layout.fragment_enter_number
    override fun buttonText(): String = getString(R.string.continuee)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as AuthActivity
    }

    override fun initViews() {
        enter_button.setOnClickListener(this)
        enterTextView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.enter_button -> enterButtonAction()
            R.id.enterTextView -> enterTextViewAction()
        }
    }

    private fun enterTextViewAction() {
        owner.addFragment(
            AuthorizationFragment.newInstance(),
            R.id.container,
            AuthorizationFragment.FRAGMENT_ID
        )
    }

    private fun enterButtonAction() {
        if (NumberValidator.isValidNumber(enter_number.text.toString())) {
            (activity as AuthActivity).saveNumber(enter_number)
            (activity as AuthActivity)
                .addFragment(
                    EnterSmsCodeFragment.INSTANCE,
                    R.id.container,
                    EnterSmsCodeFragment.FRAGMENT_ID
                )
        } else showToast(getString(R.string.number_exeption))
    }


}