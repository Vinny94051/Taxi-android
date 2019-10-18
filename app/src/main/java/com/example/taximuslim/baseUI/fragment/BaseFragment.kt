package com.example.taximuslim.baseUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(),IBaseFragment{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutId(),container,false)

    override fun showToast(message: String) = Toast.makeText(activity,message,Toast.LENGTH_LONG).show()


}