package com.example.taximuslim.presentation.view.auth.fragments.base

import android.os.Bundle
import android.view.View
import com.example.taximuslim.baseUI.fragment.BaseFragment

abstract class BaseAuthFragment : BaseFragment(), View.OnClickListener,
    IInitViews {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
}