package humc.lab.aef.core.ext.invoker

import humc.lab.aef.core.ext.api.Combinable


/**
 * @author: humingchuan
 * @date: 2023-11-18 17:24
 * @description
 */
interface ExtensionObserver<E : Combinable<E>, R> {
    fun before(ext: humc.lab.aef.core.ext.ExtImpl, args: Array<Any?>?): ProcessTag
    fun after(ext: humc.lab.aef.core.ext.ExtImpl, ret: R?): ResultHolder<R?>
}

class ResultHolder<T>(
    var ret: T,
    var processTag: ProcessTag
)

