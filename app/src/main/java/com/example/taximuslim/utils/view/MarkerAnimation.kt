package com.example.taximuslim.utils.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.taximuslim.R

@SuppressLint("StaticFieldLeak")
object MarkerAnimation {

    private lateinit var layout: RelativeLayout

    fun startAnimation(rootView: RelativeLayout) {
        layout = RelativeLayout(rootView.context)
        createLayout(rootView.context)
        layout.visibility = View.VISIBLE
        rootView.addView(layout)
    }

    fun hideMarker() {
        layout.visibility = View.GONE
    }

    fun showMarker() {
        layout.visibility = View.VISIBLE
    }


    private fun createLayout(context: Context) {

        layout.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT)

        val circle = ImageView(context)
        circle.setImageDrawable(context.getDrawable(R.drawable.circle_vector))
        createAnimation(circle, 6.0f, 6.0f, 0.1f, 1000)
        layout.addView(circle, params)


        val circle2 = ImageView(context)
        circle2.setImageDrawable(context.getDrawable(R.drawable.circle_vector))
        createAnimation(circle2, 3.0f, 3.0f, 0.3f, 1000)
        layout.addView(circle2, params)


        val circle3 = ImageView(context)
        circle3.setImageDrawable(context.getDrawable(R.drawable.circle_vector))
        layout.addView(circle3, params)

    }


    private fun createAnimation(
        view: View,
        scaleX: Float,
        scaleY: Float,
        alpha: Float,
        duration: Long
    ) {
        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("scaleX", scaleX),
            PropertyValuesHolder.ofFloat("scaleY", scaleY),
            PropertyValuesHolder.ofFloat("Alpha", alpha)
        )

        scaleDown.apply {
            this.duration = duration
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = FastOutSlowInInterpolator()
        }
        scaleDown.startDelay = 1200
        scaleDown.start()
    }
}