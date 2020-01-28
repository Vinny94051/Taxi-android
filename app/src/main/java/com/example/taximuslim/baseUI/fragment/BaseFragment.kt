package com.example.taximuslim.baseUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.utils.showToast

abstract class BaseFragment : Fragment() {

    open fun buttonText(): String = ""

    @LayoutRes
    abstract fun layoutId(): Int


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (buttonText() != "")
            setButtonText(buttonText())
    }

    fun showToast(message: String) = activity?.showToast(message)

    private fun setButtonText(text: String) {
        val buttonText: TextView? = view?.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = text
        }
    }

    fun changeEditTextTint(editText: EditText, @ColorRes color: Int) =
        DrawableCompat.setTint(editText.background, ContextCompat.getColor(context!!, color))

    fun changeViewTint(view : View, @ColorRes color : Int) =
        DrawableCompat.setTint(view.background, ContextCompat.getColor(context!!, color))

}