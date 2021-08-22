package com.remidosol.yemeksepeti_graduation_remidosol.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.utils.ThemeUtils

class SharedPreferencesManager(context: Context) {
    companion object {
        const val TOKEN = "com.remidosol.TOKEN"
        const val THEME = "com.remidosol.THEME"
        const val RESTAURANTS_PAGE = "com.remidosol.RESTAURANTS_PAGE"
        const val USER = "com.remidosol.USER"
        const val CART = "com.remidosol.CART"
    }

    private var sharedPreferences: SharedPreferences? =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun initSharedPreferences() {
        sharedPreferences?.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            Log.v("SharedPref", "$key changed ${sharedPreferences.all}")
        }
    }

    fun getToken(): String? {
        return sharedPreferences?.getString(TOKEN, "")
    }

    fun saveToken(token: String) {
        sharedPreferences?.let {
            val editor = it.edit()
            editor.putString(TOKEN, token)
            editor.apply()
        }
    }

    fun getTheme(): String? {
        return sharedPreferences?.getString(THEME, ThemeUtils.LIGHT)
    }

    fun getUser(): User? {
        val jsonUser = sharedPreferences?.getString(USER, null)
        return Gson().fromJson(jsonUser, User::class.java)
    }

    fun saveUser(user: User) {
        val jsonUser = Gson().toJson(user)
        sharedPreferences?.edit()?.putString(USER, jsonUser)?.apply()
    }

    fun removeUser() {
        sharedPreferences?.edit()?.putString(USER, "")?.apply()
    }

    fun getCart(): ArrayList<Food>? {
        val jsonCart = sharedPreferences?.getString(CART, null)
        return Gson().fromJson(jsonCart, ArrayList::class.java as Class<ArrayList<Food>>)
    }

    fun addToCart(food: Food) {
        val jsonCart = sharedPreferences?.getString(CART, null)
        val jsonCartArr = Gson().fromJson(jsonCart, ArrayList::class.java as Class<ArrayList<Food>>)
        jsonCartArr.add(food)
        sharedPreferences?.edit()?.putString(CART, Gson().toJson(jsonCartArr))?.apply()
    }

    fun removeItemFromCart(food: Food) {
        val jsonCart = sharedPreferences?.getString(CART, null)
        val jsonCartArr = Gson().fromJson(jsonCart, ArrayList::class.java as Class<ArrayList<Food>>)
        jsonCartArr.remove(food)
        sharedPreferences?.edit()?.putString(CART, Gson().toJson(jsonCartArr))?.apply()
    }

    fun clearCart() {
        sharedPreferences?.edit()?.putString(CART, "")?.apply()
    }

    fun saveTheme(theme: String) {
        sharedPreferences?.let {
            val editor = it.edit()
            editor.putString(THEME, theme)
            editor.apply()
        }
    }

    fun savePage(pageNumber: Int = 1) {
        sharedPreferences?.let {
            val editor = it.edit()
            editor
                .putInt(RESTAURANTS_PAGE, pageNumber)
            editor.apply()
        }
    }

    fun getPage(): Int {
        return sharedPreferences?.getInt(RESTAURANTS_PAGE, 1) ?: 1
    }

    fun getString(key: String, defaultVal: String = ""): String {
        return sharedPreferences?.getString(key, defaultVal) ?: ""
    }

    fun saveString(key: String, value: String) {
        sharedPreferences?.let {
            val editor = it.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }

    fun remove(key: String) = sharedPreferences?.edit()?.remove(key)?.apply()
}