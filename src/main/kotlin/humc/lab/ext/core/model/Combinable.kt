package humc.lab.ext.core.model

import humc.lab.ext.core.invoker.*

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-20 12:58
 * @description
 */
interface Combinable<E : Combinable<E>> {
    fun getCode(): String
    fun <T> first(callable: E.() -> T): T? {
        return InvokerFacade.first(callable, getCode())
    }

    fun <T> firstNonNull(callable: E.() -> T): T? {
        return FirstNonNullInvoker<E, T>().invoke(callable, getCode())
    }

    fun <T> all(callable: E.() -> T): T? {
        return AllInvoker<E, T>().invoke(callable, getCode())
    }

    fun <T> allUntil(
        checker: Function1<T?, Boolean>, callable: E.() -> T
    ): T? {
        return AllUntilInvoker<E, T>(checker).invoke(callable, getCode())
    }

    fun <T> allResult(
        callable: E.() -> T
    ): List<T> {
        return AllResultInvoker<E, T>().invoke(callable, getCode())
    }
}