package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Token(
    @SerializedName("type")
    val type: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("expires_at")
    val expires_at: String,

    @SerializedName("expires_in")
    val expires_in: Int,
): Parcelable
