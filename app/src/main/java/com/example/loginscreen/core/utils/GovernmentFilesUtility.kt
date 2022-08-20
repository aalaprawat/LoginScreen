package com.example.loginscreen.core.utils

import androidx.annotation.NonNull
import java.util.regex.Pattern

object GovernmentFilesUtility {

    const val panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}"

    fun validatePAN(@NonNull panNumber: String): Boolean {
        val regex = panPattern

        val p = Pattern.compile(regex)
        val m = p.matcher(panNumber)
        return m.matches()
    }

}