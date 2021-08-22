package com.remidosol.yemeksepeti_graduation_remidosol.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.remidosol.yemeksepeti_graduation_remidosol.data.ApiRepository
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {

    fun register(
        userBody: Map<String, @JvmSuppressWildcards RequestBody>
    ): LiveData<Resource<AuthResponse>> {
        return apiRepository.register(userBody)
    }
}