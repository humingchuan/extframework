package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.api.Combinable
import javafx.scene.paint.Stop
import org.springframework.stereotype.Component


/**
 * @author: humingchuan
 * @date: 2023-11-19 14:01
 * @description
 */
@Component
class AllInvoker(
    private val invoker: ObservableExtensionInvoker
) {
    private val stopAfterFirstUntil: ExtensionObserver = getObserver()

    private final fun getObserver() = object : ExtensionObserver {
        override fun before(ext: humc.lab.aef.core.ext.ExtImpl, args: Array<Any?>?): ProcessTag {
            return ProcessTag.goOn()
        }

        override fun <T> after(ext: humc.lab.aef.core.ext.ExtImpl, ret: T?): ResultHolder<T?> {
            return ResultHolder(ret, ProcessTag.goOn())
        }
    }

    fun <T> invoke(scenario: String, code: String, args: Array<Any?>?): T? {
        return invoker.invoke(code, args, listOf(stopAfterFirstUntil))
    }

}