package com.example.taximuslim.presentation.view.splashscreen.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.taximuslim.R

import com.example.taximuslim.baseUI.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlin.random.Random

class SplashScreenFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.to_order_main_btn -> {
                startActivity(
                    Intent(
                        this.activity!!,
                        Class.forName("com.example.taximuslim.presentation.view.main.MapsController")
                    )
                )
            }
        }
    }

    override fun layoutId() = R.layout.fragment_splash_screen

    private var backgrounds: Array<Int?>? = null
    private var wishes: Array<String>? = null

    companion object {
        const val FRAGMENT_ID = "SPLASH_SCREEN_FRAGMENT"
        val INSTANCE = SplashScreenFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createBackAndWish()
    }

    private fun initViews() = to_order_main_btn.setOnClickListener(this)

    private fun createBackAndWish() {
        initBackgrounds()
        initWishes()
        initViews()
        val fragmentNumber = Random.nextInt(0, 11)
        setBackAndWish(backgrounds!![fragmentNumber], wishes!![fragmentNumber])

    }

    private fun initBackgrounds() {
        backgrounds = arrayOf(
            R.drawable.w_1,
            R.drawable.w__2,
            R.drawable.w_3,
            R.drawable.w_4,
            R.drawable.w_5,
            R.drawable.w_6,
            R.drawable.w_7,
            R.drawable.w_8,
            R.drawable.w_9,
            R.drawable.w_10,
            R.drawable.w_11
        )
    }

    private fun initWishes() {
        wishes = arrayOf(
            getString(R.string.wish_one),
            getString(R.string.wish_two),
            getString(R.string.wish_three),
            getString(R.string.wish_four),
            getString(R.string.wish_five),
            getString(R.string.wish_six),
            getString(R.string.wish_seven),
            getString(R.string.wish_eight),
            getString(R.string.wish_nine),
            getString(R.string.wish_ten),
            getString(R.string.wish_eleven)
        )
    }

    private fun setBackAndWish(back: Int?, wish: String) {
        splash_screen_layout.background = context?.getDrawable(back!!)
        wish_text_view.text = wish
    }
}