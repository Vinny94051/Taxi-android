package com.example.taximuslim.presentation.view.main

import android.os.Bundle
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.utils.toEditable
import kotlinx.android.synthetic.main.fragment_choose_address.*

class FloatFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.lineLayout -> {
                (activity as MapsActivity).hideFloatView()
            }
        }
    }

    companion object{
        const val ID = "FLOAT_FRAGMENT"
        val INSTANCE = FloatFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_choose_address

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        pointBLocationEditText.requestFocus()
        userLocationEditText.text = (activity as MapsActivity).userLocation.toEditable()
    }



    private fun initViews(){
        lineLayout.setOnClickListener(this)
    }
}