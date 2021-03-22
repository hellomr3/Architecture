package com.looptry.architecture.model.mapper

/**
 * Author: mr.3
 * Date:
 * Desc: 抽象的Entity->业务的转换类
 * Modify By:
 * Modify Date:
 */
@FunctionalInterface
interface Mapper<I, O> {
    fun map(input: I): O
}