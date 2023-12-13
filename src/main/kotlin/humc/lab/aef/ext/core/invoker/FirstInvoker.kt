package humc.lab.aef.ext.core.invoker

import humc.lab.ext.core.api.cfg.Combinable
import humc.lab.ext.core.model.ExtImpl

/**
 * @author: humingchuan
 * @date: 2023-11-19 13:55
 * @description
 */
class FirstInvoker<E : Combinable<E>, R> {
    private val invoker: ObservableExtensionInvoker<E, R>

    init {
        val stopAfterFirst = object : ExtensionObserver<E, R> {
            override fun before(ext: ExtImpl, args: Array<Any?>?): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: ExtImpl, ret: R?): ResultHolder<R?> {
                return ResultHolder(ret, ProcessTag.stop())
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirst))
    }

    fun invoke(scenario: String, code: String, args: Array<Any?>?): R? {
        return invoker.invoke(scenario, code, args)
    }
}