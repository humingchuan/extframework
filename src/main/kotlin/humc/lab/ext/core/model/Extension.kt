package humc.lab.ext.core.model

import humc.lab.ext.core.invoker.InvokerFacade
import humc.lab.ext.core.invoker.AllInvoker
import humc.lab.ext.core.invoker.AllResultInvoker
import humc.lab.ext.core.invoker.AllUntilInvoker
import humc.lab.ext.core.invoker.FirstNonNullInvoker

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:39
 * @description
 */
interface Extension<E : Extension<E>> {
    // TODO: 拓展点要不要建模 ？
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
        checker: Function1<T?, Boolean>,
        callable: E.() -> T
    ): T? {
        return AllUntilInvoker<E, T>(checker).invoke(callable, getCode())
    }

    fun <T> allResult(
        callable: E.() -> T
    ): List<T> {
        return AllResultInvoker<E, T>().invoke(callable, getCode())
    }
}