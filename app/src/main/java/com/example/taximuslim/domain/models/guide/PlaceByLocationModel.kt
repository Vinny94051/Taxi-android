package com.example.taximuslim.domain.models.guide


data class PlaceByLocationModel (
    val placeId : Int,
    val category : String,
    val name : String,
    val text : String,
    val address : String,
    val latitude : Double,
    val longitude : Double,
    val imageUrl : String,
    val distance : Double
)