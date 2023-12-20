package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.ExtImpl
import humc.lab.aef.core.ext.api.Combinable
import org.springframework.stereotype.Component


/**
 * @author: humingchuan
 * @date: 2023-11-19 13:55
 * @description
 */
@Component
class AllUntilInvoker(
    private val invoker: ObservableExtensionInvoker
) {

    private final fun getObserver(condition: (Any?) -> Boolean) = object : ExtensionObserver {
        override fun before(ext: ExtImpl, args: Array<Any?>?): ProcessTag {
            return ProcessTag.goOn()
        }

        override fun <R> after(ext: ExtImpl, ret: R?): ResultHolder<R?> {
            if (!condition(ret)) {
                return ResultHolder(ret, ProcessTag.stop())
            }
            return ResultHolder(null, ProcessTag.goOn())
        }
    }

    fun <R> invoke(code: String, args: Array<Any?>?, condition: (R?) -> Boolean): R? {
        return invoker.invoke(code, args, listOf(getObserver(condition as (Any?) -> Boolean)))
    }
}