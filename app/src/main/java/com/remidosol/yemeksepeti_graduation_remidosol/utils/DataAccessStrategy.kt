package com.remidosol.yemeksepeti_graduation_remidosol.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.common.AuthResponse
import kotlinx.coroutines.Dispatchers


fun <T> performNetworkOperation(call: suspend () -> Resource<T>): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    "Error: ${networkCall.message}"
                )
            )
        }
    }
}

fun <T> performAuthTokenNetworkOperation(
    call: suspend () -> Resource<T>,
    saveToken: (token: String) -> Unit,
): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!

            if (data is AuthResponse) {
                data.data?.token?.let { saveToken(it.token) }
            }
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    "Error: ${networkCall.message}"
                )
            )
        }
    }
}