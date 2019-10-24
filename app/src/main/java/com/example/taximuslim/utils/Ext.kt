package com.example.taximuslim.utils

import android.location.Location
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.model.LatLng


fun String.makeStringBold(): Spanned = Html.fromHtml("<b>".plus(this).plus("</b>"))
fun String.createString(str: String): Spanned =
    Html.fromHtml("<b>".plus(this).plus("</b>").plus("\n").plus(str))

fun getSpannedText(text: String): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }

fun Button.setSpannedText(text: Spanned) {
    this.text = text
}

fun Location.toLatLng(): LatLng = LatLng(this.latitude, this.longitude)

fun EditText.onSubmit(func: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE)
            func()
        true
    }
}

fun EditText.onSubmitNext(func: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_NEXT)
            func()
        true
    }
}


