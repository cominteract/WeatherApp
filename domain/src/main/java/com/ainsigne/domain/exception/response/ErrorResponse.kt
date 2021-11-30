package com.ainsigne.domain.exception.response

data class ErrorResponse(
    val message: String? = null,
    val exception: Throwable? = null
)
