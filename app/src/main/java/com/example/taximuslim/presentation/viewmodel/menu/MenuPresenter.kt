package com.example.taximuslim.presentation.viewmodel.menu

import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.presentation.view.menu.lists.guiderecycler.GuideModel

class MenuPresenter {
    val placesForGuideRecyclerLiveData = MutableLiveData<ArrayList<GuideModel>>()


    fun loadPlaces() {

    }

}