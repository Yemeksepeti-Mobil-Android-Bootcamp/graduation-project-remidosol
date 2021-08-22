package com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("restaurantId")
    val restaurantId: Int,
    @SerializedName("total")
    val total: Double,
    @SerializedName("orderNote")
    val orderNote: String,
    @SerializedName("orderPaymentMethod")
    val orderPaymentMethod: PaymentMethods?,
    @SerializedName("restaurant")
    val restaurant: Restaurant?,
    @SerializedName("orderFoods")
    val orderFoods: List<Food>?,
    @SerializedName("user")
    val user: User?,
): Parcelable {
    enum class PaymentMethods {
        CREDIT_CARD,
        CASH,
        COUPON,
        WALLET,
    }
}