package com.example.taximuslim.utils

import android.app.Activity
import android.content.res.Resources
import android.database.Observable
import android.location.Location
import android.os.Build
import android.text.*
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import io.reactivex.subjects.ReplaySubject
import java.math.RoundingMode
import java.text.DecimalFormat


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

fun Marker?.isNotEmpty(): Boolean {
    if (this == null)
        return false

    return true
}

fun Activity.hideActionBar() {
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}


fun EditText.cursorToEnd() = this.setSelection(this.text.toString().length)

fun EditText.spellcheckingOff() {
    this.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
}

fun Int.toDp() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() = (this / Resources.getSystem().displayMetrics.density).toInt()




