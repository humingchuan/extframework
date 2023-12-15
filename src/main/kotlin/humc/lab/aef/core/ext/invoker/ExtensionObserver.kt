package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.ExtImpl


/**
 * @author: humingchuan
 * @date: 2023-11-18 17:24
 * @description
 */
interface ExtensionObserver {
    fun before(ext: ExtImpl, args: Array<Any?>?): ProcessTag
    fun <R> after(ext: ExtImpl, ret: R?): ResultHolder<R?>
}

class ResultHolder<T>(
    var ret: T,
    var processTag: ProcessTag
)

