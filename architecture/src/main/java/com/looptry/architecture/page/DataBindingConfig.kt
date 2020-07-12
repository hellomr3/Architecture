package com.looptry.architecture.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

class DataBindingConfig(
    val layout: Int,
    val variableId: Int,
    val stateViewModel: ViewModel
) {
    //绑定页面上更多的参数
    val bindingParams = SparseArray<Any>()

    /**
     * 设置variableId对应的param参数
     * 并在BasicActivity的onCreate中
     * 将参数绑定到页面
     */
    fun addBindingParams(variableId: Int, param: Any) {
        bindingParams.put(variableId, param)
    }
}