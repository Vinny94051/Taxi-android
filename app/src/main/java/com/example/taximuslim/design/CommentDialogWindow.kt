package com.example.taximuslim.design

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.comment_dialog_window.*

class CommentDialogWindow(context: Context) : ParentDialog(context) {
    override fun onClick(btn: View?) {
        when(btn?.id){
            R.id.ok_btn_1 -> {
                dismiss()
            }
            R.id.cancel_btn_1 -> {
                dismiss()
            }
        }
    }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_dialog_window)
        thisWindow = this.window
        setLayout()
    }

    override fun onStart() {
        super.onStart()
        showKeyboardFrom()
        initViews(ok_btn_1,cancel_btn_1)
    }


}