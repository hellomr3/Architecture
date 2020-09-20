package com.looptry.architecture.livedata

/**
 * Author: mr.3
 * Date:
 * Desc:事件包装类,实现数据中处理一次
 * Modify By:
 * Modify Date:
 */


open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandle(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
