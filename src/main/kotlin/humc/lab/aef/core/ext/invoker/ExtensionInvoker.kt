package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.api.Combinable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * @author: humingchuan
 * @date: 2023-11-19 14:27
 * @description
 */
@Component
class ExtensionInvoker {
    @Autowired
    private lateinit var firstInvoker: FirstInvoker

    @Autowired
    private lateinit var allInvoker: AllInvoker
    fun <E : Combinable<E>, T> first(code: String, args: Array<Any?>?): T? {
        return firstInvoker.invoke(code, args)
    }
//
//        fun <E : Combinable<E>, T> firstNonNull(scenario: String, code: String, vararg args: Any?): T? {
//            return FirstNonNullInvoker<E, T>().invoke(callable, code)
//        }

    fun <E : Combinable<E>, T> all(code: String, args: Array<Any?>?): T? {
        return allInvoker.invoke(code, args)
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