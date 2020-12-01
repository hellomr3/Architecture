package com.looptry.demo

import androidx.activity.viewModels
import com.looptry.architecture.page.BasicActivity
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.demo.R.*

class MainActivity : BasicActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, layout.activity_main, viewModel)
    }

}