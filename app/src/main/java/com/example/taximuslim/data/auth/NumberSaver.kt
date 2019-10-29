package com.example.taximuslim.data.auth

import android.util.Log
import com.example.taximuslim.baseUI.model.Saver

class NumberSaver : Saver<String> {
    /**
     * Stub
     * */
    override fun save(data : String) {
        Log.d("NUMBER:::", data)
    }
}