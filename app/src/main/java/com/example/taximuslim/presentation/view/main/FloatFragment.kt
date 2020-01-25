package com.example.taximuslim.presentation.view.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.utils.onSubmit
import com.example.taximuslim.utils.onSubmitNext
import com.example.taximuslim.utils.toEditable
import kotlinx.android.synthetic.main.fragment_choose_address.*

class FloatFragment : BaseFragment(), View.OnClickListener {

    companion object {
        const val ID = "FLOAT_FRAGMENT"
        fun newInstance() = FloatFragment()
    }

    lateinit var owner: MapsActivity

    override fun layoutId(): Int = R.layout.fragment_choose_address

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        requestFocus()
        userLocationEditText.text = (activity as MapsActivity).userLocation.toEditable()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.lineLayout -> {
                (activity as MapsActivity).hideFloatView()
            }
        }
    }

    private fun requestFocus() {
        when (owner.forFocusEditTextId) {
            MapsActivity.EDIT_TEXT_TOP -> userLocationEditText.requestFocus()
            MapsActivity.EDIT_TEXT_BOTTOM -> pointBLocationEditText.requestFocus()
        }
    }

    private fun initViews() {
        lineLayout.setOnClickListener(this)
        pointBLocationEditText.onSubmitNext {
            owner.hideFloatView()
            owner.addMarkerOnPointB(pointBLocationEditText.text.toString())
        }
        pointBLocationEditText.addTextChangedListener { address ->
            owner.viewModel.pointBLiveData.value = address.toString()
            //TODO показывать предлагаемые адреса
            //TODO добавлять адрес из нижней вью
        }
    }
}