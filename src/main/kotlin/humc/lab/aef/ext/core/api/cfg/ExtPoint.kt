package humc.lab.aef.ext.core.api.cfg

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:19
 * @description
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ExtPoint(
    val code: String,
    val strategy: InvokeStrategy = InvokeStrategy.FIRST,
    val enabled: Boolean = true
)
