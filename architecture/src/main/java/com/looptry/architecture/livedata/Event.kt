package com.looptry.architecture.livedata

import androidx.annotation.NonNull
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Author: mr.3
 * Date:
 * Desc:事件包装类,实现数据中处理一次;线程安全;
 * Modify By:
 * Modify Date:
 */
open class Event<out T>(@NonNull private val content: T) {
    init {
        require(content != null) {
            "Event content is Null"
        }
    }

    private val hasBeenHandled: AtomicBoolean = AtomicBoolean(false)

    fun getContentIfNotHandle(): T? =
        if (hasBeenHandled.compareAndSet(false, true)) content else null

    fun peekContent(): T = content
}
