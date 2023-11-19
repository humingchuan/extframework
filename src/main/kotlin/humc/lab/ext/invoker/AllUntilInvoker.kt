package humc.lab.ext.invoker

import humc.lab.ext.core.Extension
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:01
 * @description
 */
class AllUntilInvoker<E : Extension, T>(
    private val checker: Function1<T?, Boolean>
) {
    private val invoker: ObservableExtensionInvoker<E, T>

    init {
        val stopAfterFirstUntil = object : ExtensionObserver<E, T> {
            override fun before(ext: E): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: E, ret: T?): ResultHolder<T?> {
                return if (checker.invoke(ret)) {
                    ResultHolder(ret, ProcessTag.stop())
                } else {
                    ResultHolder(ret, ProcessTag.goOn())
                }
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstUntil))
    }

    fun invoke(callable: Function1<E, T>, clazz: KClass<E>): T? {
        return invoker.invoke(callable, clazz)
    }
}
