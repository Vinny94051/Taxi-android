package com.example.taximuslim.presentation.view.auth.fragments.daughter

import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.AuthController
import com.example.taximuslim.presentation.view.auth.fragments.base.BaseAuthFragment
import kotlinx.android.synthetic.main.fragment_geo_permission_accept.*

class GeoDataFragment : BaseAuthFragment() {

    companion object {
        const val FRAGMENT_ID = "GEO_DATA_FRAGMENT"
        val INSTANCE = GeoDataFragment()
    }

    override fun layoutId() = R.layout.fragment_geo_permission_accept

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.main_button_add_geo -> {
                //TODO запрос разрешения на локацию
                (activity as AuthController).replaceFragment(
                    TOUAgreeFragment.INSTANCE,
                    R.id.container,
                    TOUAgreeFragment.FRAGMENT_ID
                )
            }
            R.id.back_btn_geo -> {
                (activity as AuthController).replaceFragment(
                    EnterSmsCodeFragment.INSTANCE,
                    R.id.container,
                    EnterSmsCodeFragment.FRAGMENT_ID
                )
            }
        }
    }

    override fun initViews() {
        main_button_add_geo.setOnClickListener(this)
        back_btn_geo.setOnClickListener(this)
    }


}