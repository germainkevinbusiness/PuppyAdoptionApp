package com.kevincodes.puppyadoptionapp.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast

object Utils {

    fun isNightModeTheme(context: Context): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> false
            else -> true
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}