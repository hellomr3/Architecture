package com.looptry.architecture.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.looptry.architecture.application.BasicApplication

abstract class BasicFragment : Fragment() {

    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        check(context is AppCompatActivity)
        mActivity = context
    }

    //viewModel
    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    //dataBinding
    protected lateinit var mBinding: ViewDataBinding
    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return mBinding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        val config = getDataBindingConfig()
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, config.layout, container, false)
        binding.setVariable(config.variableId, config.stateViewModel)
        binding.lifecycleOwner = this.viewLifecycleOwner
        config.bindingParams.forEach { key, value ->
            binding.setVariable(key, value)
        }
        mBinding = binding
    }

    //fragment provider
    val mProvider by lazy {
        ViewModelProvider(this)
    }

    inline fun <reified T : ViewModel> getFragmentViewModel(): T {
        return mProvider.get(T::class.java)
    }

    //fragment.parentActivity provider
    val mActivityProvider by lazy {
        ViewModelProvider(mActivity)
    }

    inline fun <reified T : ViewModel> getActivityViewModel(): T {
        return mActivityProvider.get(T::class.java)
    }

    inline fun <reified T : ViewModel> getAppViewModel(): T {
        check(mActivity.applicationContext is BasicApplication)
        val application = mActivity.applicationContext as BasicApplication
        return ViewModelProvider(
            application.viewModelStore,
            application.mViewModelFactory
        ).get(T::class.java)
    }

}