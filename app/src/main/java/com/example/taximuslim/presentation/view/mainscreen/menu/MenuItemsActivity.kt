package com.example.taximuslim.presentation.view.mainscreen.menu

import android.os.Bundle
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.GuideFragment
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.HelpFragment
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.HistoryFragment
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.SettingsFragment
import kotlinx.android.synthetic.main.controller_menu_items.*

class MenuItemsActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(item: View?) {
        when (item?.id) {
            R.id.back_arrow_btn -> onBackPressed()
        }
    }

    var userPlaceByLocation: UserPlaceByLocationModel? = null

    override fun layoutId() = R.layout.controller_menu_items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chooseFragment(intent.getStringExtra("fragmentId")!!)
        initViews()
    }

    private fun initViews() {
        back_arrow_btn.setOnClickListener(this)
    }

    private fun chooseFragment(fragmentId: String) {
        when (fragmentId) {
            "GUIDE_FRAGMENT" -> {
                replaceFragment(GuideFragment.newInstance(), R.id.container_menu, fragmentId)
                setHeadText(R.string.guide)
            }
            "HISTORY_FRAGMENT" -> {
                replaceFragment(HistoryFragment.INSTANCE, R.id.container_menu, fragmentId)
                setHeadText(R.string.history)
            }
            "HELP_FRAGMENT" -> {
                replaceFragment(HelpFragment.INSTANCE, R.id.container_menu, fragmentId)
                setHeadText(R.string.help)
            }
            "SETTINGS_FRAGMENT" -> {
                replaceFragment(SettingsFragment.INSTANCE, R.id.container_menu, fragmentId)
                setHeadText(R.string.settings)
            }
        }
    }

    private fun setHeadText(textId: Int) {
        text_head_menu.text = getString(textId)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.last().tag == GuideFragment.FRAGMENT_ID)
            finish()
        else super.onBackPressed()

    }

}