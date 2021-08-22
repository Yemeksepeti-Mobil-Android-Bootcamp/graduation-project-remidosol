package com.remidosol.yemeksepeti_graduation_remidosol.data

import android.content.Context
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.login.LoginRequest
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.LocalDataSource
import com.remidosol.yemeksepeti_graduation_remidosol.data.local.SharedPreferencesManager
import com.remidosol.yemeksepeti_graduation_remidosol.data.remote.RemoteDataSource
import com.remidosol.yemeksepeti_graduation_remidosol.utils.performAuthTokenNetworkOperation
import com.remidosol.yemeksepeti_graduation_remidosol.utils.performNetworkOperation
import okhttp3.RequestBody
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
    fun register(registerRequest: Map<String, @JvmSuppressWildcards RequestBody>) =
        performAuthTokenNetworkOperation(
            call =
            {
                remoteDataSource.register(request = registerRequest)
            },
            saveToken =
            {
                localDataSource.saveToken(it)
            }
        )

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.login(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun listRestaurants(page: Int) = performNetworkOperation(
        call = {
            remoteDataSource.listRestaurants(page)
        }
    )

    fun getRestaurant(restaurant_id: Int) = performNetworkOperation(
        call = {
            remoteDataSource.getRestaurant(restaurant_id)
        }
    )

    fun logout() = performNetworkOperation(
        call = {
            remoteDataSource.logout()
        }
    )

    fun updateUser(userId: Int, updateUserRequest: Map<String, @JvmSuppressWildcards RequestBody>) =
        performNetworkOperation {
            remoteDataSource.updateUser(userId = userId, userBody = updateUserRequest)
        }

    fun checkToken(): String? {
        return localDataSource.getToken()
    }

    fun checkTheme(): String? {
        return localDataSource.getTheme()
    }

    fun setTheme(theme: String) = localDataSource.saveTheme(theme)

    fun getSharedPreferences(ctx: Context): SharedPreferencesManager {
        return SharedPreferencesManager(ctx)
    }

    fun getUser(): User? {
        return localDataSource.getUser()
    }

    fun saveUser(user: User) = localDataSource.saveUser(user)

    fun removeUser() = localDataSource.removeUser()

    fun getCart(): ArrayList<Food>? = localDataSource.getCart()

    fun addToCart(food: Food) {
        localDataSource.addToCart(food)
    }

    fun removeItemFromCart(food: Food) = localDataSource.removeItemFromCart(food)

    fun clearCart() = localDataSource.clearCart()
}