package com.whitessmoke.melisearcher.ext

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.whitessmoke.melisearcher.R

fun ViewGroup.snackBar(str: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snV = Snackbar.make(this, str, length)
    val sbView: View = snV.getView()
    sbView.setBackgroundColor(resources.getColor(R.color.green_meli))
    snV.show()
}