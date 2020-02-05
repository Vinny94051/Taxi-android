package com.example.taximuslim.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

fun String.toBase64(): String = byteArrayToBase64(bitmapToByteArray(this))

private fun bitmapToByteArray(imagePath: String): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    BitmapFactory.decodeFile(imagePath)
        .compress(
            Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream
        )
    return byteArrayOutputStream.toByteArray()
}

private fun byteArrayToBase64(image: ByteArray): String =
    Base64.encodeToString(image, Base64.DEFAULT)