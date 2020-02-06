package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.presentation.viewmodel.menu.GuideViewModel
import kotlinx.android.synthetic.main.fragment_guide.*

class GuideFragment : BaseFragment() {

    companion object {
        const val FRAGMENT_ID = "GUIDE_FRAGMENT"
        fun newInstance() = GuideFragment()
    }

    private val viewModel = GuideViewModel()
    private val adapter =
        GuideAdapter()

    override fun layoutId() = R.layout.fragment_guide

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initList()
    }

    private fun initViewModel() {
        viewModel.categoriesLiveData.observe(this, Observer { categories ->
            adapter.submitList(categories)
        })
    }

    private fun initList() {
        guide_recycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        guide_recycler.adapter = adapter
        viewModel.loadGuideCategories()
    }
}