package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
)
