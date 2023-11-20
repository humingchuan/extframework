package humc.lab.ext.core.model

import humc.lab.ext.core.invoker.InvokerFacade
import humc.lab.ext.core.invoker.AllInvoker
import humc.lab.ext.core.invoker.AllResultInvoker
import humc.lab.ext.core.invoker.AllUntilInvoker
import humc.lab.ext.core.invoker.FirstNonNullInvoker

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:39
 * @description
 */
interface Extension<E : Extension<E>> : Combinable<E> {
    // TODO: 拓展点要不要建模 ？

}