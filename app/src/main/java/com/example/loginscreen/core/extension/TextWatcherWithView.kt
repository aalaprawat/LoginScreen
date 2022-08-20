package com.example.loginscreen.core.extension

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

abstract class TextWatcherWithView(private val view: TextInputEditText) : TextWatcher {

    override fun afterTextChanged(p0: Editable?) {
        onTextChanged(view, p0);
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    abstract fun onTextChanged(view: TextInputEditText, p0: Editable?)
}