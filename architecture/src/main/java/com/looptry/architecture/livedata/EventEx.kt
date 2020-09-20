package com.looptry.architecture.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

typealias EventMutableLiveData<T> = MutableLiveData<Event<T>>

typealias EventLiveData<T> = LiveData<Event<T>>

@MainThread
inline fun <T> EventLiveData<T>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandle()?.let { data ->
            onChanged.invoke(data)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T> T.toEvent() = Event(this)