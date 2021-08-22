package com.remidosol.yemeksepeti_graduation_remidosol.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.di.DatabaseModule
import com.remidosol.yemeksepeti_graduation_remidosol.di.NetworkModule
import com.remidosol.yemeksepeti_graduation_remidosol.utils.ThemeUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseModule: DatabaseModule

    @Inject
    lateinit var networkModule: NetworkModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = databaseModule.sharedPrefManager(baseContext)
        sharedPref.initSharedPreferences()

        ThemeUtils(sharedPref).onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_main)

        var hello = CircularProgressIndicator(baseContext)

    }
}