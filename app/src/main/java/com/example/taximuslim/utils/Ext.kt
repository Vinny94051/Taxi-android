package com.example.taximuslim.utils

import android.app.Activity
import android.location.Location
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

fun String.toEditable(): Editable = SpannableStringBuilder(this)

fun Activity.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun LatLng.toLocation(): Location {
    val location = Location("")
    location.latitude = this.latitude
    location.longitude = this.longitude
    return location
}




