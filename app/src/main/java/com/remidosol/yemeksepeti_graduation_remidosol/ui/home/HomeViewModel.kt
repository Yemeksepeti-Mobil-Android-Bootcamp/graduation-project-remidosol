package com.remidosol.yemeksepeti_graduation_remidosol.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.remidosol.yemeksepeti_graduation_remidosol.data.ApiRepository
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
) : ViewModel() {

    fun logout(): LiveData<Resource<AuthResponse>> {
        return apiRepository.logout()
    }

    fun checkTheme(): String? {
        return apiRepository.checkTheme()
    }

    fun setTheme(theme: String) {
        return apiRepository.setTheme(theme)
    }

    fun getSharedPreferences(ctx: Context): SharedPreferencesManager {
        return apiRepository.getSharedPreferences(ctx)
    }

    fun getUser(): User? {
        return apiRepository.getUser()
    }

    fun removeUser() = apiRepository.removeUser()

}