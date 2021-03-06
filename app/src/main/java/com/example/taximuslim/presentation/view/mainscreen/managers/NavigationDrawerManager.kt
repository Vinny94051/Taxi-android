package com.example.taximuslim.presentation.view.mainscreen.managers

import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout


class NavigationDrawerManager  {

    companion object{
        private var isDrawerOpen = false

        fun showNavigationDrawer(drawerLayout : DrawerLayout) {
            if (!isDrawerOpen) {
                drawerLayout.visibility = View.VISIBLE
                drawerLayout.openDrawer(Gravity.LEFT, true)
                isDrawerOpen = true
            }else if(isDrawerOpen) {
                drawerLayout.closeDrawer(Gravity.LEFT, true)
                drawerLayout.visibility = View.GONE
                isDrawerOpen = false
            }
        }

        fun navigationDrawerStateListener(drawerLayout : DrawerLayout) {
            drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    if (newState == DrawerLayout.STATE_SETTLING && drawerLayout.isDrawerOpen(
                            GravityCompat.START
                        )
                    ) {
                        drawerLayout.closeDrawer(Gravity.LEFT, true)
                        drawerLayout.visibility = View.GONE
                        isDrawerOpen = false
                    }
                }
            })
        }

    }



}