package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Token
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenResponse(
    @SerializedName("token")
    val token: Token,

    @SerializedName("user")
    val user: User,
): Parcelable
