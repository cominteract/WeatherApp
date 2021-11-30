package com.ainsigne.domain.exception

import com.ainsigne.domain.exception.response.ErrorResponse

open class ServiceException(
    errorResponse: ErrorResponse
) : RuntimeException(errorResponse.message)
