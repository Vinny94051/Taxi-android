package com.example.taximuslim.presentation.view.design.dialogswindow

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.design.parents.ParentDialog
import com.example.taximuslim.utils.PriceHolder
import kotlinx.android.synthetic.main.dialog_price_window.*

class PriceAlert(context: Context) : ParentDialog(context) {
    override fun onClick(btn: View?) {
        when (btn?.id) {
            R.id.cancel_btn -> {
                closeListener?.invoke(true)
                dismiss()
            }
            R.id.ok_btn -> {
                closeListener?.invoke(true)
                priceLiveData.value = setPrice.text
                dismiss()
            }
        }
    }

    companion object {
        var priceLiveData = MutableLiveData<Editable>()
    }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
    }

    var priceForHint: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_price_window)
        thisWindow = this.window
        setLayout()
        initButtons(ok_btn, cancel_btn)
    }

    override fun onStart() {
        super.onStart()
        showKeyboardFrom()
        priceForHint = PriceHolder.price
        setTexts()


    }


    private fun setTexts() {
        hint.text = context.getString(R.string.hint_dialog).plus("\n")
            .plus(context.getString(R.string.second_hint_dialog))
        dialog_head2.text = "Минимальная цена ".plus(priceForHint.toString())
    }

}