package humc.lab.aef.core.ext.invoker

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
    private val neverStop: ExtensionObserver = getObserver()

    private final fun getObserver() = object : ExtensionObserver {
        override fun before(ext: humc.lab.aef.core.ext.ExtImpl, args: Array<Any?>?): ProcessTag {
            return ProcessTag.goOn()
        }

        override fun <T> after(ext: humc.lab.aef.core.ext.ExtImpl, ret: T?): ResultHolder<T?> {
            return ResultHolder(ret, ProcessTag.goOn())
        }
    }

    fun <T> invoke(code: String, args: Array<Any?>?): T? {
        return invoker.invoke(code, args, listOf(neverStop))
    }

}