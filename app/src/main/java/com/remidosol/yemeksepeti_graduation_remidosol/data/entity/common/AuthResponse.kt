package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: TokenResponse?,
): Parcelable
