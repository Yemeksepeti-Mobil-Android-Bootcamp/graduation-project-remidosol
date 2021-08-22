package com.remidosol.yemeksepeti_graduation_remidosol.data.remote

import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.EntityBaseResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.login.LoginRequest
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.register.RegisterRequest
import com.remidosol.yemeksepeti_graduation_remidosol.utils.BaseDataSource
import com.remidosol.yemeksepeti_graduation_remidosol.utils.InputStreamRequestBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: GraduationApiService) :
    BaseDataSource() {

    suspend fun register(request: Map<String, @JvmSuppressWildcards RequestBody>) = getResult { apiService.register(request) }

    suspend fun login(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun getRestaurant(restaurant_id: Int) = getResult {
        apiService.getRestaurant(restaurant_id)
    }

    suspend fun listRestaurants(page: Int) = getResult {
        apiService.listRestaurants(page)
    }

    suspend fun logout() = getResult {
        apiService.logout()
    }

    suspend fun updateUser(
        userId: Int,
        userBody: Map<String, @JvmSuppressWildcards RequestBody>
    ) = getResult {
        apiService.updateUser(userId, userBody)
    }

}