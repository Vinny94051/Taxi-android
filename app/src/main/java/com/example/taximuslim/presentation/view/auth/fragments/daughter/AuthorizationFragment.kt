package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.domain.auth.models.RegistrationStatus
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

class AuthorizationFragment : BaseAuthFragment() {
    override fun buttonText(): String = getString(R.string.continuee)

    companion object {
        const val FRAGMENT_ID = "AUTH_FRAGMENT"
        val INSTANCE = AuthorizationFragment()
        const val INVALID_NUMBER = "invalid number"
        const val EMPTY_NUMBER = "empty number"
    }

    override fun layoutId() = R.layout.fragment_authorization


    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: AuthViewModel


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button -> {
                when(val number = getAndValidatePhoneNumber()){
                    EMPTY_NUMBER -> showErrorView(getString(R.string.error_message_empty_number))
                    INVALID_NUMBER -> showErrorView(getString(R.string.error_message_invalid_number))
                    else -> loadNumberStatus(number)
                }
            }
            R.id.registrationTextView -> {
                (activity as AuthActivity).replaceFragment(
                    StartFragment.INSTANCE,
                    R.id.container,
                    StartFragment.FRAGMENT_ID
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun getAndValidatePhoneNumber(): String =
        when {
            phoneNumberEditText.text.isNotEmpty() && phoneNumberEditText.text.length == 10 -> phoneNumberEditText.text.toString()
            phoneNumberEditText.text.isEmpty() -> EMPTY_NUMBER
            else -> INVALID_NUMBER
        }


    private fun loadNumberStatus(phoneNumber: String) {
        viewModel.loadRegistrationNumberStatus(phoneNumber)
    }

    private fun initViewModel() {
        viewModel.loadRegistrationNumberStatus.observe(this, Observer { registrationStatus ->
            registrationStatus?.let { status ->
                when (status) {
                    RegistrationStatus.ENTRY -> {
                        showToast("1")
                    }
                    RegistrationStatus.REGISTRATION -> {
                        showErrorView(getString(R.string.error_message_no_account))
                    }
                    RegistrationStatus.ERROR -> {
                        showErrorView(getString(R.string.error_message_internet_exeption))
                    }
                }
            }
        })
    }

    private fun showErrorView(errorMessage: String) {
        changeEditTextTint(phoneNumberEditText, R.color.red)
        phoneNumberEditText.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_error_auth,
            0
        )
        errorTextView.text = errorMessage
        setOnKeyDown()
    }

    private fun showGreenView() {
        changeEditTextTint(phoneNumberEditText, R.color.colorThemeGreen)
        phoneNumberEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
        errorTextView.text = ""
    }

    private fun setOnKeyDown() =
        phoneNumberEditText.setOnKeyListener { _, _, _ ->
            showGreenView()
            phoneNumberEditText.setOnKeyListener { _, _, _ ->
                false
            }
            false
        }


    override fun initViews() {
        button.setOnClickListener(this)
        registrationTextView.setOnClickListener(this)
    }
}