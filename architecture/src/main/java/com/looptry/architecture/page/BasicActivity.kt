package com.looptry.architecture.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BasicActivity : AppCompatActivity() {
    //binding
    protected lateinit var mBinding: ViewDataBinding

    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    /**
     * 初始binding设置
     */
    private fun initBinding() {
        val config = getDataBindingConfig()
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, config.layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(config.variableId, config.stateViewModel)
        config.bindingParams.forEach { key, value ->
            binding.setVariable(key, value)
        }
        mBinding = binding
    }

}