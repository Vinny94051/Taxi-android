package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.fragment_enter_sms_code.*
import android.text.Editable
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel


class EnterSmsCodeFragment : BaseAuthFragment() {

    private val presenter = AuthViewModel()
    private var smsCode: Int? = null

    companion object {
        const val FRAGMENT_ID = "ENTER_SMS_CODE_FRAGMENT"
        val INSTANCE = EnterSmsCodeFragment()
    }

    override fun buttonText(): String = getString(R.string.con_tinue)

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
                    .replaceFragment(
                        EnterNumberFragment.INSTANCE, R.id.container,
                        EnterNumberFragment.FRAGMENT_ID
                    )
            }
            R.id.main_button_registration -> {
                if (isSmsCodeRight())
                    (activity as AuthActivity)
                        .replaceFragment(
                            GeoDataFragment.INSTANCE,
                            R.id.container,
                            GeoDataFragment.FRAGMENT_ID
                        )
                else showToast(getString(R.string.sms_code_exception))
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
    }

    private fun initPresenter() {
        presenter.liveDataSmsCode.observe(activity!!, Observer { liveSmsCode ->
            smsCode = liveSmsCode
        })
        presenter.loadSmsCode()
    }


    private fun getUserCode(): Int {
        val firstNum = first_num.text.toString()
        val secondNum = second_num.text.toString()
        val thirdNum = third_num.text.toString()
        val fourthNum = fourth_num.text.toString()

        if (firstNum.isNotEmpty() &&
            secondNum.isNotEmpty() &&
            thirdNum.isNotEmpty() &&
            fourthNum.isNotEmpty()
        ) {
            return (firstNum + secondNum + thirdNum + fourthNum).toInt()
        }
        return 0
    }

    private fun isSmsCodeRight() = getUserCode() == smsCode

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

