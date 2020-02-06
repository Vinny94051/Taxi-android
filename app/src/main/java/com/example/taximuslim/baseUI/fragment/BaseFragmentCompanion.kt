package com.example.taximuslim.baseUI.fragment

import androidx.fragment.app.Fragment

abstract class BaseFragmentCompanion {
    abstract val ID: String
    abstract fun newInstance(): Fragment
}
