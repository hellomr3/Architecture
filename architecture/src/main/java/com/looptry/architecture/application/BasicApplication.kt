package com.looptry.architecture.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class BasicApplication : Application(), ViewModelStoreOwner {

    protected lateinit var mViewModelStore: ViewModelStore

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