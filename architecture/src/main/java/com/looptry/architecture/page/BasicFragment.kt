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

abstract class BasicFragment : Fragment() {

    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        check(context is AppCompatActivity)
        mActivity = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            DataBindingUtil.inflate(inflater, config.layoutId, container, false)
        binding.setVariable(config.variableId, config.stateViewModel)
        binding.lifecycleOwner = this.viewLifecycleOwner
        config.bindingParams.forEach { key, value ->
            binding.setVariable(key, value)
        }
        binding.executePendingBindings()
        mBinding = binding
    }
}