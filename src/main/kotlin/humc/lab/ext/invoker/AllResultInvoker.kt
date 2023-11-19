package humc.lab.ext.invoker

import humc.lab.ext.core.Extension
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:01
 * @description
 */
class AllResultInvoker<E : Extension, T>(
    private val checker: Function1<T?, Boolean>
) {
    private val invoker: ObservableExtensionInvoker<E, T>

    private val resultList: MutableList<T> = mutableListOf()

    init {
        val stopAfterFirstUntil = object : ExtensionObserver<E, T> {
            override fun before(ext: E): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun after(ext: E, ret: T?): ResultHolder<T?> {
                if (ret != null) {
                    resultList.add(ret)
                }
                return ResultHolder(ret, ProcessTag.goOn())
            }
        }
        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstUntil))
    }

    fun invoke(callable: Function1<E, T>, clazz: KClass<E>): List<T> {
        invoker.invoke(callable, clazz)
        return resultList
    }
}
