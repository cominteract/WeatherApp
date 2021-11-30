package com.ainsigne.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
val dispatcherUnconfined: CoroutineDispatcher = Dispatchers.Unconfined
val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
