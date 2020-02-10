package com.example.taximuslim.baseUI.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposableList = mutableListOf<Disposable>()
    fun addDisposable(disposable: Disposable) {
        disposableList.add(disposable)
    }

    fun onDestroy() {
        disposableList.forEach { item ->
            item.dispose()
        }
    }
}