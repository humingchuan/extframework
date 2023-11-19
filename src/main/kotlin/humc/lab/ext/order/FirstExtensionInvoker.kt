package humc.lab.ext.order

import humc.lab.ext.core.Extension
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-18 16:36
 * @description
 */
class FirstExtensionInvoker<T>() {
    fun <E : Extension> invoke(callable: Function1<E, T>, clazz: KClass<E>): T? {
        val shouldStop: StopChecker = object : StopChecker {
            override fun shouldStop(): Boolean {
                return true
            }
        }

        val retProcess: RetProcess<T> = object : RetProcess<T> {
            override fun processRet(ret: T) {

            }
        }

        return ExtensionInvoker<T>(InvokeStrategy(shouldStop, retProcess)).invoke(callable, clazz)
    }
}
