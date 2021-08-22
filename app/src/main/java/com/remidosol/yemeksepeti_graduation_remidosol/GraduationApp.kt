package com.remidosol.yemeksepeti_graduation_remidosol

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GraduationApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}