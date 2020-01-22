package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.domain.models.RegistrationStatus
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

class AuthorizationFragment : BaseAuthFragment() {

    companion object {
        const val FRAGMENT_ID = "AUTH_FRAGMENT"
        val INSTANCE = AuthorizationFragment()
    }

    override fun layoutId() = R.layout.fragment_authorization


    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: AuthViewModel


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.enter_button -> {
                loadNumberStatus(getPhoneNumber())

            }
            R.id.registrationTextView -> {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun getPhoneNumber() : String = phoneNumberEditText.text.toString()

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
                        showToast("pls reg")
                    }
                    RegistrationStatus.ERROR -> {
                        showToast("3")
                    }
                }
            }
        })
    }

    override fun initViews() {
        enter_button.setOnClickListener(this)
        registrationTextView.setOnClickListener(this)
    }
}