package com.looptry.architecture

import android.os.Bundle
import androidx.activity.viewModels
import com.looptry.architecture.page.BasicActivity
import com.looptry.architecture.page.DataBindingConfig

class MainActivity : BasicActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_main, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}