package com.remidosol.yemeksepeti_graduation_remidosol.data.local

import com.google.gson.Gson
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharedPrefManager: SharedPreferencesManager) {

    fun initSharedPreferences() {
        return sharedPrefManager.initSharedPreferences()
    }

    fun getToken(): String? {
        return sharedPrefManager.getToken()
    }

    fun getTheme(): String? {
        return sharedPrefManager.getTheme()
    }

    fun getPage(): Int {
        return sharedPrefManager.getPage()
    }

    fun getString(key: String): String? {
        return sharedPrefManager.getString(key)
    }

    fun getUser(): User? {
        return sharedPrefManager.getUser()
    }

    fun saveUser(user: User) = sharedPrefManager.saveUser(user)

    fun removeUser() = sharedPrefManager.removeUser()

    fun saveTheme(theme: String) = sharedPrefManager.saveTheme(theme)

    fun savePage(pageNumber: Int) = sharedPrefManager.savePage(pageNumber)

    fun saveToken(token: String) = sharedPrefManager.saveToken(token)

    fun saveString(key: String, value: String) = sharedPrefManager.saveString(key, value)

    fun remove(key: String) = sharedPrefManager.remove(key)

    fun getCart(): ArrayList<Food>? {
        return sharedPrefManager.getCart()
    }

    fun addToCart(food: Food) {
        sharedPrefManager.addToCart(food)
    }

    fun removeItemFromCart(food: Food) = sharedPrefManager.removeItemFromCart(food)

    fun clearCart() {
        sharedPrefManager.clearCart()
    }
}