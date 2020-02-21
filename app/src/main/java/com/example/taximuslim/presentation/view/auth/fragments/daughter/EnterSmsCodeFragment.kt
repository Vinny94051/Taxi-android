package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.fragment_enter_sms_code.*
import android.text.Editable
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presentation.view.splashscreen.SplashScreenActivity
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import com.example.taximuslim.utils.prefference.saveVerToken
import com.example.taximuslim.utils.toEditable
import javax.inject.Inject


class EnterSmsCodeFragment : BaseAuthFragment() {

    companion object {
        const val FRAGMENT_ID = "ENTER_SMS_CODE_FRAGMENT"
        val INSTANCE = EnterSmsCodeFragment()
    }

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var viewModel: AuthViewModel


    override fun buttonText(): String = getString(R.string.continuee)

    override fun initViews() {
        addTextChangedListener(first_num, second_num)
        addTextChangedListener(second_num, third_num)
        addTextChangedListener(third_num, fourth_num)
        main_button_registration.setOnClickListener(this)
        back_btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.back_btn -> {
                (activity as AuthActivity)
                    .addFragment(
                        EnterNumberFragment.INSTANCE, R.id.container,
                        EnterNumberFragment.FRAGMENT_ID
                    )
            }
            R.id.main_button_registration -> {
                (activity as AuthActivity).userNumber?.let { number ->
                    viewModel.loadToken(CheckSmsCodeRequest(number, getUserCode()))
                }
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.loadTokenLiveData.observe(this as LifecycleOwner, Observer { token ->

            when (token) {
                "{status=no code}" -> showErrorView(getString(R.string.error_message_bad_code))
                else -> {
                    saveVerToken(context!!, token)
                    openNextPage()
                }
            }
        })
    }

    private fun showErrorView(error: String) {
        first_num.text = "".toEditable()
        second_num.text = "".toEditable()
        third_num.text = "".toEditable()
        fourth_num.text = "".toEditable()
        first_num.requestFocus()
        changeEditTextTint(first_num, R.color.red)
        changeEditTextTint(second_num, R.color.red)
        changeEditTextTint(third_num, R.color.red)
        changeEditTextTint(fourth_num, R.color.red)
        setOnKeyListener(first_num)
        setOnKeyListener(second_num)
        setOnKeyListener(third_num)
        setOnKeyListener(fourth_num)
        setOnKeyListener(first_num)
        errorMessageTextView.visibility = View.VISIBLE
        errorMessageTextView.text = error
    }

    private fun setOnKeyListener(editText: EditText) {
        editText.setOnKeyListener { _, _, _ ->
            hideErrorView()
            false
        }
    }

    private fun hideErrorView() {
        changeEditTextTint(first_num, R.color.sms_input_tint)
        changeEditTextTint(second_num, R.color.sms_input_tint)
        changeEditTextTint(third_num, R.color.sms_input_tint)
        changeEditTextTint(fourth_num, R.color.sms_input_tint)
        errorMessageTextView.visibility = View.GONE
    }

    private fun openNextPage() {
        if ((activity as AuthActivity).isAuth) {
            startActivity(Intent(activity, SplashScreenActivity::class.java))
            activity?.finish()
        } else
            (activity as AuthActivity)
                .addFragment(
                    GeoDataFragment.INSTANCE,
                    R.id.container,
                    GeoDataFragment.FRAGMENT_ID
                )
    }


    private fun getUserCode(): String {
        val firstNum = first_num.text.toString()
        val secondNum = second_num.text.toString()
        val thirdNum = third_num.text.toString()
        val fourthNum = fourth_num.text.toString()

        if (firstNum.isNotEmpty() &&
            secondNum.isNotEmpty() &&
            thirdNum.isNotEmpty() &&
            fourthNum.isNotEmpty()
        ) {
            return (firstNum + secondNum + thirdNum + fourthNum)
        }
        return ""
    }

    override fun layoutId() = R.layout.fragment_enter_sms_code


    private fun addTextChangedListener(editTextAddListener: EditText, editTextReqFocus: EditText) {
        editTextAddListener.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO смена цвета нижнего подчеркивания на зеленый
                editTextReqFocus.requestFocus()
            }
        })
    }


}

