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
class CommonInvoker(
    private val invoker: ObservableExtensionInvoker
) {

    fun <R> invoke(code: String, args: Array<Any?>?, observer: ExtensionObserver): R? {
        return invoker.invoke(code, args, listOf(observer))
    }
}