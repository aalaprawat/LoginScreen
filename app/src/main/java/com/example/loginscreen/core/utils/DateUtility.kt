package com.example.loginscreen.core.utils

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import java.text.ParseException
import java.text.SimpleDateFormat

object DateUtility {
    @SuppressLint("SimpleDateFormat")
    @NonNull
    fun validate(@Nullable dateString: String?): Boolean {
        if (dateString == null)
            return false
        if (dateString.length != 10)
            return false
        try {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            simpleDateFormat.isLenient = false;
            val date = simpleDateFormat.parse(dateString)
            if (date != null) {
                return date < simpleDateFormat.parse("01/01/2010")
            }
            return false
        } catch (exception: ParseException) {
            return false
        }
    }

}