package com.remidosol.yemeksepeti_graduation_remidosol.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager

class ThemeUtils(sharedPref: SharedPreferencesManager) {

    companion object {
        const val LIGHT: String = "LIGHT"
        const val DARK: String = "DARK"
    }

    private var cTheme: String = sharedPref.getTheme()!!

    fun changeTheme(activity: AppCompatActivity, theme: String = LIGHT) {
        cTheme = theme

        activity.finish().also {
            activity.startActivity(Intent(activity.baseContext, activity.javaClass))
        }
    }

    fun onActivityCreateSetTheme(activity: AppCompatActivity) {
        when (cTheme) {
            LIGHT -> {
                activity.setTheme(R.style.Theme_Yemeksepeti_graduation_remidosol_LIGHT)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK -> {
                activity.setTheme(R.style.Theme_Yemeksepeti_graduation_remidosol_DARK)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                activity.setTheme(R.style.Theme_Yemeksepeti_graduation_remidosol_LIGHT)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
