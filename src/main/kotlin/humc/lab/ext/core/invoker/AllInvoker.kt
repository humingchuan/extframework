package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Combinable
import humc.lab.ext.core.model.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:01
 * @description
 */
class AllInvoker<E : Combinable<E>, T>(
) {
    private val invoker: ObservableExtensionInvoker<E, T>

    init {
        val stopAfterFirstUntil = object : ExtensionObserver<E, T> {
            override fun before(ext: E): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: E, ret: T?): ResultHolder<T?> {
                return ResultHolder(ret, ProcessTag.goOn())
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstUntil))
    }

    fun invoke(callable: Function1<E, T>, code: String): T? {
        return invoker.invoke(callable, code)
    }
}
