package humc.lab.ext.core.model

import java.lang.annotation.Inherited

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:19
 * @description
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Spi(
    val code: String = ""
)
