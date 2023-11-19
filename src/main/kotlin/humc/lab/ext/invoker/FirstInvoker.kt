package humc.lab.ext.invoker

import humc.lab.ext.core.Extension
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 13:55
 * @description
 */
class FirstInvoker<E : Extension, R> {
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

    fun invoke(callable: Function1<E, R>, clazz: KClass<E>): R? {
        return invoker.invoke(callable, clazz)
    }
}