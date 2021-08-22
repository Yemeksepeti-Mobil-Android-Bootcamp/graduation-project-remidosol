package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("arrivalTime")
    val arrivalTime: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("logoUrl")
    val logoUrl: String,
    @SerializedName("restaurantAddress")
    val restaurantAddress: List<Address>?,
    @SerializedName("restaurantFoods")
    val restaurantFoods: List<Food>?,
): Parcelable
