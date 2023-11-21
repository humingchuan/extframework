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
        fun <E : Combinable<E>, T> first(scenario: String, code: String, args: Array<Any?>?): T? {
            return FirstInvoker<E, T>().invoke(scenario, code, args)
        }
//
//        fun <E : Combinable<E>, T> firstNonNull(scenario: String, code: String, vararg args: Any?): T? {
//            return FirstNonNullInvoker<E, T>().invoke(callable, code)
//        }

        fun <E : Combinable<E>, T> all(scenario: String, code: String, args: Array<Any?>?): T? {
            return AllInvoker<E, T>().invoke(scenario, code, args)
        }
//
//        fun <E : Combinable<E>, T> allUntil(
//            checker: Function1<T?, Boolean>,
//            scenario: String, code: String, vararg args: Any?
//        ): T? {
//            return AllUntilInvoker<E, T>(checker).invoke(callable, code)
//        }
//
//        fun <E : Combinable<E>, T> allResult(
//            scenario: String, code: String, vararg args: Any?
//        ): List<T> {
//            return AllResultInvoker<E, T>().invoke(callable, code)
//        }
    }

}