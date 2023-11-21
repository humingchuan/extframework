package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Combinable
import humc.lab.ext.core.model.ExtImpl
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
            override fun before(ext: ExtImpl, args: Array<Any?>?): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: ExtImpl, ret: T?): ResultHolder<T?> {
                return ResultHolder(ret, ProcessTag.goOn())
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstUntil))
    }

    fun invoke(scenario: String, code: String, args: Array<Any?>?): T? {
        return invoker.invoke(scenario, code, args)
    }
}
