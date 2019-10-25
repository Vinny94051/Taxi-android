package com.example.taximuslim.design

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager

open class ParentDialog(context: Context) : AlertDialog(context), View.OnClickListener {
    override fun onClick(btn: View?) {}

    var thisWindow: Window? = null

    fun setLayout() =
        thisWindow?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    fun initViews(okBtn : View, cancelBtn: View) {
        okBtn.setOnClickListener(this)
        cancelBtn.setOnClickListener(this)
    }

    fun showKeyboardFrom() {
        thisWindow?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        thisWindow?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }
}