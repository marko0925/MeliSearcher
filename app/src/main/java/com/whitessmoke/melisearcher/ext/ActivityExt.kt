package com.whitessmoke.melisearcher.ext

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Archivo de extension para actividades
 */
/**
 * Crea un toast mas facil de implementar
 * @param str mensaje del Toast
 * @param length duración del Toast
 */
fun Activity.toast(str: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, str, length).show()
}

