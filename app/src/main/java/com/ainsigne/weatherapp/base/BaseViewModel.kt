package com.ainsigne.weatherapp.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking

abstract class BaseViewModel : ViewModel() {
    fun emitState(
        emitAction: suspend () -> Unit
    ) = runBlocking {
        emitAction()
    }

    open fun showErrorMessage(withHideLoader: Boolean = true, errorMsg: Throwable) {}
}
