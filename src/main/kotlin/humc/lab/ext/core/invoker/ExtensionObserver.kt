package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Combinable
import humc.lab.ext.core.model.Extension


/**
 * @author: humingchuan
 * @date: 2023-11-18 17:24
 * @description
 */
interface ExtensionObserver<E : Combinable<E>, R> {
    fun before(ext: E): ProcessTag
    fun after(ext: E, ret: R?): ResultHolder<R?>
}

class ResultHolder<T>(
    var ret: T,
    var processTag: ProcessTag
)

