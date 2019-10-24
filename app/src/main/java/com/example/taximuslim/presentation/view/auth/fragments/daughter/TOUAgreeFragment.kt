package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.content.Intent
import android.text.Html
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_terms_of_use_agree.*

class TOUAgreeFragment : BaseAuthFragment() {
    override fun layoutId() = R.layout.fragment_terms_of_use_agree

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_button_tou_agree -> {
                if (checkBox.isChecked) {
                    val intent = Intent(
                        this.activity,
                        Class.forName("com.example.taximuslim.presentation.view.splashscreen.SplashScreenController")
                    )
                    startActivity(intent)
                } else showToast(getString(R.string.tou_exception))
            }
            R.id.checkBox -> {
                (activity as AuthController).replaceFragment(
                    TOUTextFragment.INSTANCE, R.id.container, TOUTextFragment.FRAGMENT_ID
                )
            }
        }
    }

    override fun initViews() {
        main_button_tou_agree.setOnClickListener(this)
        checkBox.text =
            Html.fromHtml("Я ознакомлен с <b>Правилами использования</b>, принимаю их условия и выражаю свое согласие на обработку моих персональных данных")
        checkBox.setOnClickListener(this)
    }

    companion object {
        const val FRAGMENT_ID = "TERMS_OF_USE_AGREE_FRAGMENT"
        val INSTANCE = TOUAgreeFragment()
    }
}