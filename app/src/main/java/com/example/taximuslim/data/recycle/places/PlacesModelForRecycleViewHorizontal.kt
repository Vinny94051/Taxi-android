package com.example.taximuslim.data.recycle.places

import com.example.taximuslim.R

class PlacesModelForRecycleViewHorizontal constructor(var img: Int, var text: String) {


    /**
     * Load data by server
     * */
     fun load(listener: (ArrayList<PlacesModelForRecycleViewHorizontal>) -> Unit) {
        //getData and invoke listener
        val data = arrayListOf(
            PlacesModelForRecycleViewHorizontal(R.drawable.mecheti, "Мечети"),
            PlacesModelForRecycleViewHorizontal(R.drawable.moleln_rooms,"Молельные комнаты"),
            PlacesModelForRecycleViewHorizontal(R.drawable.culture, "Культура"),
            PlacesModelForRecycleViewHorizontal(R.drawable.shops, "Магазины"),
            PlacesModelForRecycleViewHorizontal(R.drawable.cafe_and_restouronts, "Кафе и рестораны")
        )
        listener.invoke(data)
    }
}