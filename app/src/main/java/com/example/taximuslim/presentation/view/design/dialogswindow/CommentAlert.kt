package com.example.taximuslim.presentation.view.design.dialogswindow

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.design.parents.ParentDialog
import com.example.taximuslim.presentation.viewmodel.maps.MainViewModel
import kotlinx.android.synthetic.main.comment_dialog_window.*

class CommentAlert(context: Context) : ParentDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
    }

    companion object {
        val toDriverCommentLiveData = MutableLiveData<Editable>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_dialog_window)
        setCancelable(false)
        window?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    override fun onStart() {
        super.onStart()
        showKeyboardFrom()
        initButtons(ok_btn_1, cancel_btn_1)
        initCommentInput()
    }

    override fun onClick(btn: View?) {
        when (btn?.id) {
            R.id.ok_btn_1 -> {
                closeListener?.invoke(true)
                dismiss()
            }

            R.id.cancel_btn_1 -> {
                closeListener?.invoke(true)
                dismiss()
            }
        }
    }


    private fun initCommentInput() {
        commentEditText.addTextChangedListener { comment ->
            toDriverCommentLiveData.value = comment
        }
    }


}