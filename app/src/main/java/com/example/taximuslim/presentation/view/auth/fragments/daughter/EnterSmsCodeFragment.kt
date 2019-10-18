package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.text.TextWatcher
import android.view.View
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.fragment_enter_sms_code.*
import android.text.Editable
import android.widget.EditText
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment


class EnterSmsCodeFragment : BaseAuthFragment() {

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
                (activity as AuthController)
                    .replaceFragment(
                        EnterNumberFragment.INSTANCE, R.id.container,
                        EnterNumberFragment.FRAGMENT_ID
                    )
            }
            R.id.main_button_registration -> {
                if(isSmsCodeRight())
                (activity as AuthController)
                    .replaceFragment(
                        GeoDataFragment.INSTANCE,
                        R.id.container,
                        GeoDataFragment.FRAGMENT_ID
                    )
                 else showToast(getString(R.string.sms_code_exception))
            }

        }
    }

    private fun isSmsCodeRight() = true

    override fun layoutId() = R.layout.fragment_enter_sms_code

    companion object {
        const val FRAGMENT_ID = "ENTER_SMS_CODE_FRAGMENT"
        val INSTANCE = EnterSmsCodeFragment()
    }


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

