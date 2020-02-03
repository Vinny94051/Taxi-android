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
            PlacesModelForRecycleViewHorizontal(R.drawable.mecheti, "Молельные комнаты"),
            PlacesModelForRecycleViewHorizontal(R.drawable.mecheti, "Культура"),
            PlacesModelForRecycleViewHorizontal(R.drawable.mecheti, "Магазины"),
            PlacesModelForRecycleViewHorizontal(R.drawable.mecheti, "Кафе и рестораны")
        )
        listener.invoke(data)
    }
}