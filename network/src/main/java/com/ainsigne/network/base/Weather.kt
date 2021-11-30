package com.ainsigne.network.base

import com.ainsigne.domain.extension.EMPTY

data class Weather(
    val id: Int = 0,
    val main: String = EMPTY,
    val description: String = EMPTY,
    val icon: String = EMPTY,
)
