package com.example.taximuslim.data.recycle.places

import com.example.taximuslim.R

class PlacesModelForRecycleViewVertical constructor(val img : Int, val text : String) {

     fun load(listener: (ArrayList<PlacesModelForRecycleViewVertical>) -> Unit) {
        val data = arrayListOf(
            PlacesModelForRecycleViewVertical(R.drawable.mecheti, "Мечети"),
            PlacesModelForRecycleViewVertical(R.drawable.moleln_rooms,"Молельные комнаты"),
            PlacesModelForRecycleViewVertical(R.drawable.culture, "Культура"),
            PlacesModelForRecycleViewVertical(R.drawable.shops, "Магазины"),
            PlacesModelForRecycleViewVertical(R.drawable.cafe_and_restouronts, "Кафе и рестораны")
        )
        listener.invoke(data)
    }


}