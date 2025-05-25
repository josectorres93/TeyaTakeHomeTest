package com.teya.data.remote

import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        val response = apiCall.invoke()
        if ((response as Response<*>).isSuccessful) {
            ResultWrapper.Success(response)
        } else {
            val error = (response as Response<*>)
            ResultWrapper.GenericError(
                code = error.code(),
                error = error?.errorBody()?.string()
                    .toString()
            )
        }

    } catch (exception: Exception) {
        when (exception) {
            is IOException -> {
                Log.e("ApiCall", exception.toString())
                ResultWrapper.NetworkError
            }
            is HttpException -> {
                val code = exception.code()
                ResultWrapper.GenericError(code, convertErrorBody(exception))
            }
            else -> {
                ResultWrapper.GenericError(500, convertErrorBody(null))
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException?): String? {
    return try {
        if (throwable != null) {
            throwable.response()?.errorBody()?.let {
                val errorMessageID = ""
                errorMessageID
            }
        } else {
            ""
        }

    } catch (exception: Exception) {
        ""
    }
}