package com.example.taximuslim.presentation.view.auth.driver

enum class LoadingStatus {
    NULL, LOADING, COMPLETE, ERROR
}

fun LoadingStatus?.isComplete(): Boolean{
    return (this == LoadingStatus.COMPLETE)
}

