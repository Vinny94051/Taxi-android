package com.example.taximuslim.presentation.view.menu.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.view.menu.lists.guiderecycler.GuideCustomAdapter
import com.example.taximuslim.presentation.viewmodel.menu.MenuPresenter
import kotlinx.android.synthetic.main.fragment_guide.*

class GuideFragment : BaseFragment() {

    companion object {
        const val FRAGMENT_ID = "GUIDE_FRAGMENT"
        val INSTANCE = GuideFragment()
    }

    private val presenter = MenuPresenter()
    private val adapter = GuideCustomAdapter()

    override fun layoutId() = R.layout.fragment_guide

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initList()
    }

    private fun initPresenter() {
        presenter.placesForGuideRecyclerLiveData.observe(activity!!, Observer { places ->
            adapter.replaceAll(places)
        })
    }

    private fun initList() {
        guide_recycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        guide_recycler.adapter = adapter
        presenter.loadPlaces()
    }
}