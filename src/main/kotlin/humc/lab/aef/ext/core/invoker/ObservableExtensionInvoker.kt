package humc.lab.aef.ext.core.invoker

import humc.lab.aef.ext.core.ExtensionCenter
import humc.lab.ext.core.api.cfg.Combinable

/**
 * @author: humingchuan
 * @date: 2023-11-18 16:36
 * @description
 */
class ObservableExtensionInvoker<E : Combinable<E>, R>(
    private val observers: List<ExtensionObserver<E, R>>,
) {
    fun invoke(scenario: String, code: String, args: Array<Any?>?): R? {
        val extImpls = humc.lab.aef.ext.core.ExtensionCenter.getExtensions(scenario, code)
        var ret: R? = null
        for (extImpl in extImpls) {
            for (observer in observers) {
                if (observer.before(extImpl, args).shouldStop()) {
                    return ret
                }
            }

            ret = extImpl.method.invoke(args) as R?

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
