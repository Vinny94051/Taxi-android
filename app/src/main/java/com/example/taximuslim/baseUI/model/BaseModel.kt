package com.example.taximuslim.baseUI.model

abstract class BaseModel<T> {
    abstract fun load(listener: (ArrayList<T>) -> Unit)
    abstract fun save()
    abstract fun action()
}