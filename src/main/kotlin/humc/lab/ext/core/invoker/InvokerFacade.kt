package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Combinable
import humc.lab.ext.core.model.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:27
 * @description
 */
class InvokerFacade {
    companion object {
        fun <E : Combinable<E>, T> first(callable: Function1<E, T>, code: String): T? {
            return FirstInvoker<E, T>().invoke(callable, code)
        }

        fun <E : Combinable<E>, T> firstNonNull(callable: Function1<E, T>, code: String): T? {
            return FirstNonNullInvoker<E, T>().invoke(callable, code)
        }

        fun <E : Combinable<E>, T> all(callable: Function1<E, T>, code: String): T? {
            return AllInvoker<E, T>().invoke(callable, code)
        }

        fun <E : Combinable<E>, T> allUntil(
            checker: Function1<T?, Boolean>,
            callable: Function1<E, T>, code: String
        ): T? {
            return AllUntilInvoker<E, T>(checker).invoke(callable, code)
        }

        fun <E : Combinable<E>, T> allResult(
            callable: Function1<E, T>, code: String
        ): List<T> {
            return AllResultInvoker<E, T>().invoke(callable, code)
        }
    }

}