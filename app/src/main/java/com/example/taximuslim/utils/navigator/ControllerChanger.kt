package com.example.taximuslim.utils.navigator

import android.content.Intent
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.menu.MenuItemsActivity

class ControllerChanger(private val activity : BaseActivity) {
    fun openMenuController(fragmentId : String) {
        val intent = Intent(activity, MenuItemsActivity::class.java)
        intent.putExtra("fragmentId", fragmentId)
        activity.startActivity(intent)
    }
}