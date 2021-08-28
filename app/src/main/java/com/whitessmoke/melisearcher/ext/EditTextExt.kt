package com.whitessmoke.melisearcher.ext

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast

/**
 * Extensiones para los editText
 */


/**
 * Extension para simplificar el TextChangedListener
 */
fun EditText.onTextListener(listener: (query: String) -> Unit) {


    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })

}

fun EditText.onEnterListener(listener: () -> Unit) {
    this.setOnEditorActionListener { v, actionId, event ->
        println(actionId)
        if (actionId == EditorInfo.IME_ACTION_DONE) {

            listener()

        }
        return@setOnEditorActionListener false
    }

}