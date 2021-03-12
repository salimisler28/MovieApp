package com.teknasyon.movieapp.app.util

import com.teknasyon.movieapp.app.base.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> fromNetwork(
    request: suspend () -> Resource<T>
) = flow<Resource<T>> {
    emit(Resource.loading())

    val response = request.invoke()

    if (response.status == Resource.Status.SUCCESS) {
        emit(Resource.success(data = response.data))
    } else {
        emit(Resource.error(message = response.message))
    }
}.flowOn(Dispatchers.IO)