package com.android.example.mitapp.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.validateText() {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val txtInput: String = s.toString()

            if (txtInput.isEmpty()) {
                this@validateText.setError(null)
            } else
                if (txtInput.length != 6) {
                    this@validateText.setError("El usuario debe ser de tener 6 caracteres")
                } else {
                    this@validateText.setError(null)
                }

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    })
}

fun TextInputEditText.validatePWD() {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val txtInput: String = s.toString()

            if (txtInput.isEmpty()) {
                this@validatePWD.setError(null)
            } else
                if (txtInput.length != 6) {
                    this@validatePWD.setError("La contraseña debe ser de tener 6 caracteres")
                } else {
                    this@validatePWD.setError(null)
                }

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    })
}


fun TextInputEditText.validCard():Boolean{

    val txtInput: String = text.toString()
    if (txtInput.isEmpty() || txtInput.length !=16 ){return false}

   return true
}

