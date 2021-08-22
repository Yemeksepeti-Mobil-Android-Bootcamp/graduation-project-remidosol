package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    @SerializedName("id")
    val id: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("street")
    val street: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
): Parcelable
