package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String?,
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
): Parcelable
