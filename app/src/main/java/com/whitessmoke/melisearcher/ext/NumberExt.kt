package com.whitessmoke.melisearcher.ext

import java.text.NumberFormat
import java.util.*

fun Int.toPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val str = format.format(this)
    return str.substring(0, str.length - 3)

}

fun Float.toPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val str = format.format(this)
    return str.substring(0, str.length - 3)

}