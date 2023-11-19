package humc.lab.ext.core

import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-18 16:54
 * @description
 */
object ExtensionCenter {
    fun <E : Extension> getExtensions(clazz: KClass<E>): List<E> {
        return emptyList()
    }
}