package com.remidosol.yemeksepeti_graduation_remidosol.data.remote

import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.EntityBaseResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.ListingBaseResponse
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Food
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Order
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.Restaurant
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.login.LoginRequest
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface GraduationApiService {

    @Multipart
    @POST("register")
    suspend fun register(@PartMap request: Map<String, @JvmSuppressWildcards RequestBody>): Response<AuthResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("logout")
    suspend fun logout(): Response<AuthResponse>

//    @Multipart
//    @POST("api/Accounts/editaccount")
//    suspend fun uploadFile(
//        @Part("file\"; filename=\"pp.png\" ") file: RequestBody,
//        @Part("FirstName") fname: RequestBody,
//        @Part("Id") id: RequestBody
//    ): Response<LoginResponse>

    @GET("restaurants")
    suspend fun listRestaurants(@Query("page") page: Int): Response<ListingBaseResponse<Restaurant>>

    @GET("orders")
    suspend fun listOrders(@Query("page") page: Int): Response<ListingBaseResponse<Order>>

    @GET("restaurants/foods/{food_id}")
    suspend fun getFood(@Path("food_id") food_id: Int): Response<EntityBaseResponse<Food>>

    @GET("restaurants/{restaurant_id}")
    suspend fun getRestaurant(@Path("restaurant_id") restaurant_id: Int): Response<Restaurant>

    @GET("orders/{order_id}")
    suspend fun getOrder(@Path("order_id") order_id: Int): Response<EntityBaseResponse<Order>>

//    @GET("users/{user_id}")
//    suspend fun getUser(@Path("user_id") user_id: Int): Response<EntityBaseResponse<User>>

//    @Multipart
//    @POST("restaurants/foods/add")
//    suspend fun storeFood(
//        @PartMap foodBody: Map<String, @JvmSuppressWildcards RequestBody>
//    ): Response<EntityBaseResponse<Food>>

//    @POST("restaurants/store")
//    suspend fun storeRestaurant(
//        @Field("name") name: String,
//        @Field("category") category: String,
//        @Field("logoUrl") logoUrl: String,
//    ): Response<EntityBaseResponse<Restaurant>>

    @POST("orders/store")
    suspend fun storeOrder(
        @Field("userId") userId: Int,
        @Field("restaurantId") restaurantId: Int,
        @Field("total") total: Double,
        @Field("orderNote") orderNote: String,
        @Field("orderPaymentMethod") orderPaymentMethod: Order.PaymentMethods,
    ): Response<EntityBaseResponse<Order>>

//    @POST("users/store")
//    suspend fun storeUser(
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("firstName") firstName: String,
//        @Field("lastName") lastName: String,
//        @Field("mobileNumber") mobileNumber: String,
//        @Field("avatarUrl") avatarUrl: String,
//    ): Response<EntityBaseResponse<User>>

//    @PUT("restaurants/foods/{food_id}")
//    suspend fun updateFood(
//        @Path("food_id") food_id: Int,
//        @Field("name") name: String?,
//        @Field("price") price: Double?,
//        @Field("imageUrl") imageUrl: String?,
//    ): Response<EntityBaseResponse<Food>>

//    @POST("restaurants/update/{restaurant_id}")
//    suspend fun updateRestaurant(
//        @Path("restaurant_id") restaurant_id: Int,
//        @Field("name") name: String?,
//        @Field("typeOfRestaurant") typeOfRestaurant: String?,
//        @Field("logoUrl") logoUrl: String?,
//    ): Response<EntityBaseResponse<Restaurant>>

//    @POST("orders/update/{order_id}")
//    suspend fun updateOrder(
//        @Path("order_id") order_id: Int,
//        @Field("userId") userId: Int?,
//        @Field("restaurantId") restaurantId: Int?,
//        @Field("orderNote") orderNote: String?,
//        @Field("orderPaymentMethod") orderPaymentMethod: Order.PaymentMethods?,
//    ): Response<EntityBaseResponse<Order>>

    @Multipart
    @PUT("users/update/{user_id}")
    suspend fun updateUser(
        @Query("user_id") userId: Int,
        @PartMap userBody: Map<String, @JvmSuppressWildcards RequestBody>
//        @Path("user_id") user_id: Int,
//        @Field("email") email: String?,
//        @Field("password") password: String?,
//        @Field("firstName") firstName: String?,
//        @Field("lastName") lastName: String?,
//        @Field("mobileNumber") mobileNumber: String?,
//        @Field("avatarUrl") avatarUrl: String?,
    ): Response<EntityBaseResponse<User>>

//    @POST("restaurants/address/{restaurant_id}")
//    suspend fun storeRestaurantAddress(
//        @Path("restaurant_id") restaurant_id: Int,
//        @Field("country") country: String,
//        @Field("city") city: String,
//        @Field("neighborhood") neighborhood: String,
//        @Field("street") street: String,
//        @Field("latitude") latitude: Double?,
//        @Field("longitude") longitude: Double?,
//    ): Response<EntityBaseResponse<Restaurant>>

//    @POST("users/address/{user_id}")
//    suspend fun storeUserAddress(
//        @Path("user_id") user_id: Int,
//        @Field("country") country: String,
//        @Field("city") city: String,
//        @Field("neighborhood") neighborhood: String,
//        @Field("street") street: String,
//        @Field("latitude") latitude: Double?,
//        @Field("longitude") longitude: Double?,
//    ): Response<EntityBaseResponse<User>>

//    @DELETE("restaurants/foods/{food_id}")
//    suspend fun deleteFood(
//        @Path("food_id") food_id: Int
//    ): Response<EntityBaseResponse<Food>>

//    @DELETE("restaurants/delete/{restaurant_id}")
//    suspend fun deleteRestaurant(
//        @Path("restaurant_id") restaurant_id: Int
//    ): Response<EntityBaseResponse<Restaurant>>

//    @DELETE("orders/delete/{order_id}")
//    suspend fun deleteOrder(
//        @Path("order_id") order_id: Int
//    ): Response<EntityBaseResponse<Order>>

//    @DELETE("users/delete/{user_id}")
//    suspend fun deleteUser(
//        @Path("user_id") user_id: Int
//    ): Response<EntityBaseResponse<User>>
}