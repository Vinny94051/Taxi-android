package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.domain.auth.models.RegistrationStatus
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import com.example.taximuslim.utils.NumberValidator
import kotlinx.android.synthetic.main.fragment_enter_number.*
import javax.inject.Inject

class EnterNumberFragment : BaseAuthFragment() {

    init {
        App.appComponent.inject(this)
    }


    companion object {
        const val FRAGMENT_ID = "ENTER_NUMBER_FRAGMENT"
        val INSTANCE =
            EnterNumberFragment()

    }

    @Inject
    lateinit var viewModel: AuthViewModel

    private lateinit var owner: AuthActivity

    override fun layoutId() = R.layout.fragment_enter_number
    override fun buttonText(): String = getString(R.string.continuee)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as AuthActivity
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
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

    private fun enterButtonAction() =
        viewModel.loadRegistrationNumberStatus(enter_number.text.toString())

    private fun initViewModel() {
        viewModel.loadRegistrationNumberStatus.observe(this, Observer { response ->
            val number = enter_number.text.toString()
            when {
                response == RegistrationStatus.ENTRY -> {
                    showErrorView("Аккаунт уже существует. Выполните вход")
                }

                number == "" -> {
                    showErrorView("Введите номер телефона")
                }
                !NumberValidator.isValidNumber(number) -> {
                    showErrorView("Номер введен не верно")
                }
                else -> {
                    (activity as AuthActivity).saveNumber(enter_number)
                    (activity as AuthActivity)
                        .addFragment(
                            EnterSmsCodeFragment.INSTANCE,
                            R.id.container,
                            EnterSmsCodeFragment.FRAGMENT_ID
                        )
                }
            }
        })
    }

    private fun showErrorView(errorMessage: String) {
        changeEditTextTint(enter_number, R.color.red)
        enter_number.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_error_auth_v2,
            0
        )
        errorTextView.text = errorMessage
    }

}