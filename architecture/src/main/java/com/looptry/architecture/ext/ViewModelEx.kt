package com.looptry.architecture.ext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
fun ViewModel.launchAsync(
    context: CoroutineContext = Dispatchers.Main,
    onStart: (() -> Unit)? = null,
    onFailure: ((Throwable) -> Unit)? = null,
    onFinished: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    //监督异常
    val handler = CoroutineExceptionHandler { _, throwable ->
        onFailure?.invoke(throwable)
    }
    this.viewModelScope.launch(context + handler) {
        try {
            onStart?.invoke()
            block.invoke(this)
        } catch (e: Throwable) {
            if (e is CancellationException) {
                //doNothing
                Log.e("Arch", "CancellationException")
            } else {
                onFailure?.invoke(e)
            }
        } finally {
            try {
                onFinished?.invoke()
            } catch (e: Throwable) {
                //doNothing
            }
        }
    }
}