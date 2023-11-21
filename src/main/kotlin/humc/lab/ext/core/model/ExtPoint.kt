package humc.lab.ext.core.model

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:19
 * @description
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ExtPoint(
    val code: String,
    val strategy: InvokeStrategy = InvokeStrategy.FIRST
)
