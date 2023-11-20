package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Extension
import humc.lab.ext.core.ExtensionCenter

/**
 * @author: humingchuan
 * @date: 2023-11-18 16:36
 * @description
 */
class ObservableExtensionInvoker<E : Extension<E>, R>(
    private val observers: List<ExtensionObserver<E, R>>,
) {
    fun invoke(callable: Function1<E, R>, code: String): R? {
        val extensions = ExtensionCenter.getExtensions(code)
        var ret: R? = null
        for (extension in extensions) {
            extension as E
            for (observer in observers) {
                if (observer.before(extension).shouldStop()) {
                    return ret
                }
            }
            ret = callable.invoke(extension)

            for (observer in observers) {
                val result = observer.after(extension, ret)
                ret = result.ret
                if (result.processTag.shouldStop()) {
                    return ret
                }
            }
        }
        return ret
    }
}
