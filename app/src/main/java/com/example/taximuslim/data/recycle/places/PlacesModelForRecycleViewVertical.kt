package com.example.taximuslim.data.recycle.places

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.model.BaseModel

class PlacesModelForRecycleViewVertical constructor(val img : Int, val text : String)
    : BaseModel<PlacesModelForRecycleViewVertical>(){

    override fun load(listener: (ArrayList<PlacesModelForRecycleViewVertical>) -> Unit) {
        val data = arrayListOf(
            PlacesModelForRecycleViewVertical(R.drawable.mecheti, "Мечети"),
            PlacesModelForRecycleViewVertical(R.drawable.moleln_rooms,"Молельные комнаты"),
            PlacesModelForRecycleViewVertical(R.drawable.culture, "Культура"),
            PlacesModelForRecycleViewVertical(R.drawable.shops, "Магазины"),
            PlacesModelForRecycleViewVertical(R.drawable.cafe_and_restouronts, "Кафе и рестораны")
        )
        listener.invoke(data)
    }

    override fun save() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun action() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}