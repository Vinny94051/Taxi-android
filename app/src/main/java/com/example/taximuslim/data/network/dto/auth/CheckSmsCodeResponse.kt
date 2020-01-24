package com.example.taximuslim.data.network.dto.auth

import com.google.gson.annotations.SerializedName

data class CheckSmsCodeResponse(
    @SerializedName("status")
    val token : String
){
    override fun toString(): String = token

}