package com.looptry.architecture.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class BasicApplication : Application(), ViewModelStoreOwner {

    //全局应用范围内的ViewModelStore
    private lateinit var mViewModelStore: ViewModelStore

    val mViewModelFactory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        mViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }
}