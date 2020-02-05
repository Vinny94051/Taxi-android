package com.example.taximuslim.bindingUtils

import android.animation.Animator
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import com.squareup.picasso.Picasso

@BindingAdapter("image")
fun ImageView.setImage(uri: Uri?) {
    Glide.with(this.context)
        .load(uri)
        .placeholder(R.drawable.take_photo_placeholder)
        .into(this)
}

@BindingAdapter("profile_image")
fun ImageView.setProfileImage(uri: Uri?) {
    Glide.with(this.context)
        .load(uri)
        .placeholder(R.drawable.photo_placeholder)
        .into(this)

}

@BindingAdapter("loading_status")
fun ImageView.setStatus(status: LoadingStatus) {
    when (status) {
        LoadingStatus.ERROR -> {
            this.alpha = 1f
            Picasso.get()
                .load(R.drawable.ic_error_loading)
                .into(this)
            this.visibility = View.VISIBLE

        }
        LoadingStatus.COMPLETE -> {
            Glide.with(this)
                .load(R.drawable.ic_success_loading)
                .into(this)
            this.visibility = View.VISIBLE
            this.alpha = 1f
            this.animate()
                .setDuration(1000)
                .alpha(0f)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {
                        this@setStatus.visibility = View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {}
                })
                .start()

        }
        else -> {
            this.visibility = View.GONE
        }
    }
}


