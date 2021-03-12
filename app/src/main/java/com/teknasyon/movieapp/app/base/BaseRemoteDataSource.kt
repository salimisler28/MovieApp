package com.teknasyon.movieapp.app.base

import retrofit2.Response
import java.lang.Exception

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(
        call: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = call()

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(
                    "Error Code = ${response.code()}, " +
                            "Error Message = ${response.message()}, " +
                            "Error Body = ${response.errorBody()} + "
                )
            }
        } catch (e: Exception) {
            Resource.error(e.message)
        }
    }
}