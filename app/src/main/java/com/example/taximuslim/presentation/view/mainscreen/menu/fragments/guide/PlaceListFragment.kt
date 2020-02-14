package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.presentation.view.adapter.guide.PlaceListAdapter
import com.example.taximuslim.presentation.view.mainscreen.MapsActivity
import com.example.taximuslim.presentation.view.mainscreen.menu.MenuItemsActivity
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.SharedViewModel
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.PlaceDescriptionFragment.Companion.PLACE
import kotlinx.android.synthetic.main.fragment_place_list.*

class PlaceListFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "PLACE_LIST_FRAGMENT"

        override fun newInstance() = PlaceListFragment()
    }

    private val adapter = PlaceListAdapter()
    private lateinit var viewModel: PlacesListViewModel
    private lateinit var owner: MenuItemsActivity
    private lateinit var sharedViewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MenuItemsActivity
        viewModel = ViewModelProvider(
            this,
            PlacesListViewModelFactory(owner.userPlaceByLocation!!)
        )[PlacesListViewModel::class.java]
        lifecycle.addObserver(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViewModel()
    }

    private fun initList() {
        placesRecyclerList.layoutManager = LinearLayoutManager(context)
        placesRecyclerList.adapter = adapter

        adapter.setOnItemClickListener { place ->
            sharedViewModel.placeItem(place)
            owner.addFragment(
                PlaceDescriptionFragment.newInstance(),
                R.id.container_menu,
                PlaceDescriptionFragment.ID
            )
        }

        adapter.setOnOrderButtonClickListener { address ->
            val intent = Intent(activity, MapsActivity::class.java)
            intent.putExtra(PLACE, address)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun initViewModel() {
        viewModel.placesLiveData.observe(this, Observer { places ->
            adapter.submitList(places)
        })
    }


    override fun layoutId() = R.layout.fragment_place_list


}