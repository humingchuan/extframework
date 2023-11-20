package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 13:55
 * @description
 */
class FirstInvoker<E : Extension<E>, R> {
    private val invoker: ObservableExtensionInvoker<E, R>

    init {
        val stopAfterFirst = object : ExtensionObserver<E, R> {
            override fun before(ext: E): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: E, ret: R?): ResultHolder<R?> {
                return ResultHolder(ret, ProcessTag.stop())
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirst))
    }

    fun invoke(callable: Function1<E, R>, code: String): R? {
        return invoker.invoke(callable, code)
    }
}