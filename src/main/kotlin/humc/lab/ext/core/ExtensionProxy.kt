package humc.lab.ext.core

import humc.lab.ext.facade.InvokerFacade
import humc.lab.ext.invoker.*
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:39
 * @description
 */
abstract class ExtensionProxy<E : Extension<E>> : Extension<E> {
    // TODO: 拓展点要不要建模 ？

}