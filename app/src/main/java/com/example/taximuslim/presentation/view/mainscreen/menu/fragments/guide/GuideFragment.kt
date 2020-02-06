package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel
import com.example.taximuslim.presentation.view.adapter.guide.DrawerGuideAdapter
import com.example.taximuslim.presentation.view.mainscreen.menu.MenuItemsActivity
import kotlinx.android.synthetic.main.fragment_guide.*

class GuideFragment : BaseFragment() {

    companion object {
        const val FRAGMENT_ID = "GUIDE_FRAGMENT"
        fun newInstance() = GuideFragment()
    }

    private val viewModel = GuideViewModel()
    private val adapter = DrawerGuideAdapter()
    private lateinit var owner: MenuItemsActivity

    override fun layoutId() = R.layout.fragment_guide

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MenuItemsActivity
    }

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

        adapter.setOnItemClickListener { categoryId ->
            viewModel.currentLocation.observe(this@GuideFragment, Observer { location ->
                owner.userPlaceByLocation = UserPlaceByLocationModel(
                    categoryId,
                    55.5807418,
                    36.8237481
                 //   location.latitude,
                  //  location.longitude
                )
                owner.replaceFragment(
                    PlaceListFragment.newInstance(),
                    R.id.container_menu,
                    PlaceListFragment.ID
                )
            })
            viewModel.loadLocation()
        }
    }
}