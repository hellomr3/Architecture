package com.looptry.architecture.request

/**
 * Author: mr.3
 * Date:
 * Desc: 封装网络请求的结果
 * Modify By:
 * Modify Date:
 */
sealed class Result<T> {

    data class OK<T>(val data: T) : Result<T>()

    data class Exception(val throwable: Throwable) : Result<Nothing>()

    data class Failure(val msg: String) : Result<Nothing>()
}

inline fun <reified T> Result<T>.doOnSuccess(block: (T) -> Unit) {
    if (this is Result.OK) {
        block.invoke(this.data)
    }
}

inline fun <reified T> Result<T>.doOnException(block: (Throwable) -> Unit) {
    if (this is Result.Exception) {
        block.invoke(this.throwable)
    }
}

inline fun <reified T> Result<T>.doOnFailure(block: (String) -> Unit) {
    if (this is Result.Failure) {
        block.invoke(this.msg)
    }
}
