package com.remidosol.yemeksepeti_graduation_remidosol.di

import android.content.Context
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.LocalDataSource
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    fun localDataSource(sharedPrefManager: SharedPreferencesManager): LocalDataSource {
        return LocalDataSource(sharedPrefManager)
    }
}