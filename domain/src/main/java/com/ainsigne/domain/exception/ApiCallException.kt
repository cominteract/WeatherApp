package com.ainsigne.domain.exception

import com.ainsigne.domain.exception.helper.extractErrorDetail
import com.ainsigne.domain.exception.response.ErrorResponse
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

@Throws(ServiceException::class)
inline fun <reified T> apiSafeCall(call: () -> T) = try {
    call()
} catch (e: Exception) {
    /**
     * We can add other exception here and also if we want to check the
     * status we can also add here.
     **/
    throw ServiceException(
        when (e) {
            is UnknownHostException -> {
                ErrorResponse(
                    message = "No internet connection!",
                    exception = e.cause
                )
            }
            is SocketException -> {
                ErrorResponse(
                    message = e.message,
                    exception = e.cause
                )
            }
            is HttpException -> {
                val errorMessage = e.response()?.errorBody().extractErrorDetail() ?: e.message
                ErrorResponse(
                    message = errorMessage,
                    exception = e.cause
                )
            }
            else -> {
                ErrorResponse(
                    message = e.message,
                    exception = e.cause
                )
            }
        }
    )
}
