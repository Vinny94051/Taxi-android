package com.example.taximuslim.data.recycle.places

import com.example.taximuslim.R

class PlacesModelForRecycleViewHorizontal(var img: Int, var text: String) {

    companion object {
        /**
         * Load data by server
         * */
        fun load(listener: (ArrayList<PlacesModelForRecycleViewHorizontal>) -> Unit) {
            //getData and invoke listener
            val data = arrayListOf(
                PlacesModelForRecycleViewHorizontal(R.drawable.w_1, "first image"),
                PlacesModelForRecycleViewHorizontal(R.drawable.w_3, "second image"),
                PlacesModelForRecycleViewHorizontal(R.drawable.w_4, "third image")
            )
            listener.invoke(data)
        }
    }
}