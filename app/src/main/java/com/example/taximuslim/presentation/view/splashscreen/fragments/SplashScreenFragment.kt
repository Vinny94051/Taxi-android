package com.example.taximuslim.presentation.view.splashscreen.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.taximuslim.R

import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.view.mainscreen.MapsActivity
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenFragment : BaseFragment(), View.OnClickListener {

    //TODO fix white line on the top of the screen
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.to_order_main_btn ->
                startActivity(Intent(this.activity!!, MapsActivity::class.java))

        }
    }

    override fun buttonText(): String = getString(R.string.skip)

    override fun layoutId() = R.layout.fragment_splash_screen

    private val viewModel = AuthViewModel()

    companion object {
        const val FRAGMENT_ID = "SPLASH_SCREEN_FRAGMENT"
        val INSTANCE = SplashScreenFragment()
    }

    private fun initViewModel() {
        viewModel.loadPreseptLiveData.observe(this, Observer { presept ->
            Glide.with(context!!)
                .load(presept.imageUrl)
                .into(preseptImage)
            wish_text_view.text = presept.description
        })
    }

    private fun loadPresept() = viewModel.loadPresept()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
        loadPresept()
    }

    private fun initViews() = to_order_main_btn.setOnClickListener(this)

}