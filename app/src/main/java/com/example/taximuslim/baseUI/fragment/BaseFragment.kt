package com.example.taximuslim.baseUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.taximuslim.R

abstract class BaseFragment : Fragment(), IBaseFragment {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(buttonText() != "")
        setButtonText(buttonText())
    }

    open fun buttonText(): String = ""

    override fun showToast(message: String) =
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

    private fun setButtonText(text: String) {
        val buttonText: TextView? = view?.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = text
        }
    }

    fun changeEditTextTint(editText: EditText, @ColorRes color: Int) =
        DrawableCompat.setTint(editText.background, ContextCompat.getColor(context!!, color))
}