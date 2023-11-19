package humc.lab.ext.facade

import humc.lab.ext.core.Extension
import humc.lab.ext.invoker.*
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:27
 * @description
 */
class InvokerFacade {
    companion object {
        fun <E : Extension, T> first(callable: Function1<E, T>, clazz: KClass<E>): T? {
            return FirstInvoker<E, T>().invoke(callable, clazz)
        }

        fun <E : Extension, T> firstNonNull(callable: Function1<E, T>, clazz: KClass<E>): T? {
            return FirstNonNullInvoker<E, T>().invoke(callable, clazz)
        }

        fun <E : Extension, T> all(callable: Function1<E, T>, clazz: KClass<E>): T? {
            return AllInvoker<E, T>().invoke(callable, clazz)
        }

        fun <E : Extension, T> allUntil(
            checker: Function1<T?, Boolean>,
            callable: Function1<E, T>, clazz: KClass<E>
        ): T? {
            return AllUntilInvoker<E, T>(checker).invoke(callable, clazz)
        }

        fun <E : Extension, T> allResult(
            checker: Function1<T?, Boolean>,
            callable: Function1<E, T>, clazz: KClass<E>
        ): List<T> {
            return AllResultInvoker<E, T>(checker).invoke(callable, clazz)
        }
    }

}