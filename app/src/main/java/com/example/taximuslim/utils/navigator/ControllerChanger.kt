package com.example.taximuslim.utils.navigator

import android.content.Intent
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.presentation.view.mainscreen.menu.MenuItemsActivity

class ControllerChanger(private val activity : BaseActivity) {
    companion object{
        const val ID = "fragmentId"
        const val CATEGORY_ID = "categoryId"
    }

    fun openMenuController(fragmentId : String) {
        val intent = Intent(activity, MenuItemsActivity::class.java)
        intent.putExtra(ID, fragmentId)
        activity.startActivity(intent)
    }

    fun openMenuController(fragmentId: String, categoryId : Int){
        val intent = Intent(activity, MenuItemsActivity::class.java)
        intent.putExtra(ID, fragmentId)
        intent.putExtra(CATEGORY_ID,categoryId)
        activity.startActivity(intent)
    }
}