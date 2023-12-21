package humc.lab.aef.core.ext.invoker

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

    @Autowired
    private lateinit var commonInvoker: CommonInvoker

    @Autowired
    private lateinit var firstNonNullInvoker: FirstNonNullInvoker

    @Autowired
    private lateinit var allUntilInvoker: AllUntilInvoker
    fun <T> first(code: String, args: Array<Any?>?): T? {
        return firstInvoker.invoke(code, args)
    }

    fun <T> firstNonNull(code: String, args: Array<Any?>?): T? {
        return firstNonNullInvoker.invoke(code, args)
    }

    fun <T> until(
        code: String, args: Array<Any?>?,
        condition: (T?) -> Boolean
    ): T? {
        return allUntilInvoker.invoke(code, args, condition)
    }

    fun <T> all(code: String, args: Array<Any?>?): T? {
        return allInvoker.invoke(code, args)
    }

    fun <T> common(code: String, args: Array<Any?>?, observer: ExtensionObserver): T? {
        return commonInvoker.invoke(code, args, observer)
    }
//        fun <E : Combinable<E>, T> allResult(
//            scenario: String, code: String, vararg args: Any?
//        ): List<T> {
//            return AllResultInvoker<E, T>().invoke(callable, code)
//        }

}