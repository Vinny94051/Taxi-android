package com.example.taximuslim.baseUI.fragment

import androidx.annotation.LayoutRes

interface IBaseFragment {
    @LayoutRes
    fun layoutId(): Int
    fun showToast(message : String)
}
