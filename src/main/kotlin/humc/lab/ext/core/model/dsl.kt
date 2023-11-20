package humc.lab.ext.core.model

import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:40
 * @description
 */
fun getCode(clazz: KClass<*>): String? {
    val spi = clazz.java.getAnnotation(Spi::class.java)
    return spi?.code
}