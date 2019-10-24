package com.example.taximuslim.design

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.dialog_price_window.*

class PriceDialogWindow(context: Context) : AlertDialog(context), View.OnClickListener {
    override fun onClick(item: View?) {
        when (item?.id) {
            R.id.cancel_btn -> {
                dismiss()
            }
            R.id.ok_btn -> {
                price.value = set_price.text
        //        priceNotLiveData = set_price.text
                dismiss()
            }
        }
    }


    companion object {
        var price = MutableLiveData<Editable>()
   //     var priceNotLiveData: Editable? = null

//        fun getPrice(listener: (Editable) -> Unit) {
//            priceNotLiveData?.let { priceNotNull ->
//                listener.invoke(priceNotNull)
//            }
//        }
    }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
    }

    private var thisWindow: Window? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_price_window)
        thisWindow = this.window
        setLayout()
        initViews()
        setTexts()
    }

    override fun onStart() {
        super.onStart()
        showKeyboardFrom()
    }

    private fun setLayout() =
        thisWindow?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    private fun setTexts() {
        hint.text = context.getString(R.string.hint_dialog).plus("\n")
            .plus(context.getString(R.string.second_hint_dialog))
        dialog_head2.text = "Минимальная цена 1200"
    }

    private fun initViews() {
        ok_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    private fun showKeyboardFrom() {
        thisWindow?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        thisWindow?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

}