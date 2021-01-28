package com.looptry.architecture.ext

import android.util.Log

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

fun Any.logD(tag: String = "TAG") {
    Log.d(tag, "$this")
}

fun Any.logE(tag: String = "TAG") {
    Log.e(tag, "$this")
}