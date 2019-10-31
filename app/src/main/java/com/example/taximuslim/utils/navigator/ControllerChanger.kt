package com.example.taximuslim.utils.navigator

import android.content.Intent
import com.example.taximuslim.baseUI.controller.BaseController
import com.example.taximuslim.baseUI.fragment.BaseFragment

class ControllerChanger(private val activity : BaseController) {
    fun openMenuController(fragmentId : String) {
        val intent = Intent(activity, Class.forName(
            "com.example.taximuslim.presentation.view.menu.MenuItemsController") )
        intent.putExtra("fragmentId", fragmentId)
        activity.startActivity(intent)
    }
}