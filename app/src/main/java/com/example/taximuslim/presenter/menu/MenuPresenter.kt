package com.example.taximuslim.presenter.menu

import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.data.recycle.places.PlacesModelForRecycleViewVertical
import com.example.taximuslim.presentation.view.menu.lists.guiderecycler.GuideModel

class MenuPresenter {
    val placesForGuideRecyclerLiveData = MutableLiveData<ArrayList<GuideModel>>()

    var placesModelForRecycleViewVertical = PlacesModelForRecycleViewVertical(0, "")

    fun loadPlaces() {
        placesModelForRecycleViewVertical.load { places ->
            val placesGuide = arrayListOf<GuideModel>()
            for (place in places) {
                placesGuide.add(GuideModel(place.img, place.text))
            }
            placesForGuideRecyclerLiveData.value = placesGuide
        }
    }

}