package com.looptry.architecture.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.looptry.architecture.application.BasicApplication

abstract class BasicActivity : AppCompatActivity() {

    val mViewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(this)
    }

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

    /**
     * 获取Activity范围内的ViewModel
     */
    inline fun <reified T : ViewModel> getActivityViewModel(): T {
        return mViewModelProvider.get(T::class.java)
    }

    /**
     * 获取App范围内的ViewModel
     */
    inline fun <reified T : ViewModel> getAppViewModel(): T {
        check(applicationContext is BasicApplication)
        val application = applicationContext as BasicApplication
        return ViewModelProvider(
            application.viewModelStore,
            application.mViewModelFactory
        ).get(T::class.java)
    }
}