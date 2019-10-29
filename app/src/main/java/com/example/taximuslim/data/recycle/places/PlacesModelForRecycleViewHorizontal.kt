package com.example.taximuslim.data.recycle.places

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.model.BaseModel

class PlacesModelForRecycleViewHorizontal constructor(var img: Int, var text: String)
    : BaseModel<PlacesModelForRecycleViewHorizontal>() {

    override fun save() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun action() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Load data by server
     * */
    override fun load(listener: (ArrayList<PlacesModelForRecycleViewHorizontal>) -> Unit) {
        //getData and invoke listener
        val data = arrayListOf(
            PlacesModelForRecycleViewHorizontal(R.drawable.w_1, "first image"),
            PlacesModelForRecycleViewHorizontal(R.drawable.w_3, "second image"),
            PlacesModelForRecycleViewHorizontal(R.drawable.w_4, "third image")
        )
        listener.invoke(data)
    }
}