package com.remidosol.yemeksepeti_graduation_remidosol.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.remidosol.yemeksepeti_graduation_remidosol.data.ApiRepository
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.login.LoginRequest
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
) : ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<AuthResponse>> {
        val request = LoginRequest(email, password)
        return apiRepository.login(request)
    }

    fun getUser(): User? {
        return apiRepository.getUser()
    }

    fun saveUser(user: User) {
        apiRepository.saveUser(user)
    }
}