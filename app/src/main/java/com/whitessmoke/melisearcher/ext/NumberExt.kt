package com.whitessmoke.melisearcher.ext

import java.text.NumberFormat
import java.util.*

/**
 * Archivo de extension para Numeros
 */

/**
 * Formatea un entero a formato precio
 */
fun Int.toPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val str = format.format(this)
    return str.substring(0, str.length - 3)

}
/**
 * Formatea un flotante a formato precio
 */
fun Float.toPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val str = format.format(this)
    return str.substring(0, str.length - 3)

}