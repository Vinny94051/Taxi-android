package com.example.taximuslim.utils.prefference

import android.content.Context


private const val VERIFICATION_TOKEN : String = "VERIFICATION_TOKEN"

fun saveVerToken(context: Context, token: String) {
    val sharedPref = context.getSharedPreferences(VERIFICATION_TOKEN, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString(VERIFICATION_TOKEN, "Bearer $token")
        commit()
    }
}

fun getAuthHeader(context: Context): String =
    context.getSharedPreferences(VERIFICATION_TOKEN, Context.MODE_PRIVATE)
        .getString(VERIFICATION_TOKEN, "") ?: ""



