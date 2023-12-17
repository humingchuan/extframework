package humc.lab.aef.core.session

import humc.lab.aef.core.ext.ExtImpl
import humc.lab.aef.core.ext.invoker.ObservableExtensionInvoker
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-17 16:35
 * @description
 */
@Component
class SolutionExtensionInvoker(
    private val extensionResolver: ExtensionResolver

) : ObservableExtensionInvoker() {
    override fun resolveExtImpl(code: String, args: Array<Any?>?): List<ExtImpl> {
        return extensionResolver.resolveExtImpl(code)
    }
}