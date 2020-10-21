package com.looptry.architecture.ext

import com.looptry.architecture.livedata.Event

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

inline fun <reified T> T?.toEvent() = Event(this ?: throw Throwable("不能在Event使用空值"))
