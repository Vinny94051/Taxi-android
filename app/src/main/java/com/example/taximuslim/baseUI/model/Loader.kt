package com.example.taximuslim.baseUI.model

interface Loader<T> {
    fun load(listener : (T) -> Unit)
}