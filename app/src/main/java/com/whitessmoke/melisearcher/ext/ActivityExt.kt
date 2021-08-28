package com.whitessmoke.melisearcher.ext

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Activity.toast(str: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, str, length).show()
}

