package humc.lab.ext.invoker

import humc.lab.ext.core.Extension
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:01
 * @description
 */
class FirstNonNullInvoker<E : Extension, R> {
    private val invoker: ObservableExtensionInvoker<E, R>

    init {
        val stopAfterFirstNonNull = object : ExtensionObserver<E, R> {
            override fun before(ext: E): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: E, ret: R?): ResultHolder<R?> {
                return if (ret != null) {
                    ResultHolder(ret, ProcessTag.stop())
                } else {
                    ResultHolder(null, ProcessTag.goOn())
                }
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstNonNull))
    }

    fun invoke(callable: Function1<E, R>, clazz: KClass<E>): R? {
        return invoker.invoke(callable, clazz)
    }
}