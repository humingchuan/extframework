package humc.lab.ext.core

import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:40
 * @description
 */

fun <T : Extension> declareExt(clazz: KClass<T>): T {
    return clazz.constructors.first().call()
}