package humc.lab.aef.core.ext.api

import humc.lab.aef.core.ext.invoker.ExtensionObserver
import java.util.function.Consumer

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-20 12:58
 * @description
 */
interface Combinable<E : Combinable<E>> {
    /**
     * first返回一个对象，这个对象是一个代理，负责记录行为和参数并
     */
    fun first(): E {
        TODO()
    }

    fun <T> firstNonNull(): E {
        TODO()
    }

    fun runAll(): E {
        TODO()
    }

    fun <T> callAll(resultConsumer: Consumer<T?>): E {
        TODO()
    }

    fun <T> until(
        checker: Function1<T?, Boolean>
    ): E {
        TODO()
    }

    fun <T> customized(
        observer: ExtensionObserver
    ): E {
        TODO()
    }
}