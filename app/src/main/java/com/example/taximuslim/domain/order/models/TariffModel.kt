package com.example.taximuslim.domain.order.models

data class TariffModel(
    val economy : Int,
    val comfort : Int,
    val business : Int
){
    override fun toString(): String {
        return "TariffModel(economy=$economy, comfort=$comfort, business=$business)"
    }
}