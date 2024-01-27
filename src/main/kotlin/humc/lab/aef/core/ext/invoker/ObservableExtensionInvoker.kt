package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.ExtImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * @author: humingchuan
 * @date: 2023-11-18 16:36
 * @description
 */
abstract class ObservableExtensionInvoker(
) {
    private val log: Logger = LoggerFactory.getLogger(ObservableExtensionInvoker::class.java)
    abstract fun resolveExtImpl(
        code: String,
        args: Array<Any?>?
    ): List<ExtImpl>

    fun <R> invoke(
        code: String,
        args: Array<Any?>?,
        observers: List<ExtensionObserver>
    ): R? {
        val extImpls = resolveExtImpl(code, args)
        printExtensions(code, extImpls)

        var ret: R? = null
        for (extImpl in extImpls) {
            log.debug("Extension ${extImpl.instCode} starts")

            for (observer in observers) {
                val tag = observer.before(extImpl, args)
                if (tag.shouldStop()) {
                    log.debug("before executing ,Extension {} stopped by {} ", extImpl.instCode, observer)
                    return ret
                }
                if (tag.shouldSkip()) {
                    log.debug("before executing ,Extension {} skipped by {}", extImpl.instCode, observer)
                    continue
                }
            }

            ret = if (args == null) {
                extImpl.method.invoke(extImpl.this_) as R?
            } else {
                extImpl.method.invoke(extImpl.this_, *args) as R?
            }

            for (observer in observers) {
                val result = observer.after(extImpl, ret)
                ret = result.ret
                val tag = result.processTag
                if (tag.shouldStop()) {
                    log.debug("after executing ,Extension {} stopped by {}", extImpl.instCode, observer)
                    return ret
                }
            }
        }
        return ret
    }

    private fun printExtensions(code: String, extImpls: List<ExtImpl>) {
        if (log.isDebugEnabled) {
            val sb: StringBuilder = StringBuilder("Extension of code $code are : \n")
            extImpls.forEach { sb.append("\t ${it.instCode}@${it.scenario}\n") }
            log.debug(sb.toString())
        }
    }
}
