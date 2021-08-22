package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common

import android.os.Parcelable
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Meta
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingBaseResponse<T: Parcelable>(
    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("data")
    val data: List<T>
): Parcelable