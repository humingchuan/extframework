package humc.lab.ext.core

import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:40
 * @description
 */

fun <T : Extension<T>> declareExt(clazz: KClass<T>): T {
    val call = clazz.constructors.first().call()
    return call
}