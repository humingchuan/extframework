package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.ExtensionCenter
import humc.lab.aef.core.session.BusinessSession
import humc.lab.aef.core.session.ExtensionResolver
import org.springframework.stereotype.Component


/**
 * @author: humingchuan
 * @date: 2023-11-18 16:36
 * @description
 */
@Component
// TODO: 这里依赖关系乱了，拓展点不应该依赖会话
class ObservableExtensionInvoker(
    private val extensionResolver: ExtensionResolver
) {
    fun <R> invoke(
        code: String,
        args: Array<Any?>?,
        observers: List<ExtensionObserver>
    ): R? {
        val extImpls = extensionResolver.resolveExtImpl(code)
        var ret: R? = null
        for (extImpl in extImpls) {
            for (observer in observers) {
                if (observer.before(extImpl, args).shouldStop()) {
                    return ret
                }
            }
            if (args == null) {
                ret = extImpl.method.invoke(extImpl._this) as R?
            } else {
                ret = extImpl.method.invoke(extImpl._this, *args) as R?
            }

            for (observer in observers) {
                val result = observer.after(extImpl, ret)
                ret = result.ret
                if (result.processTag.shouldStop()) {
                    return ret
                }
            }
        }
        return ret
    }
}
