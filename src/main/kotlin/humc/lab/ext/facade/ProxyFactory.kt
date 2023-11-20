package humc.lab.ext.facade

import humc.lab.ext.core.Extension
import humc.lab.ext.core.Spi
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
class ProxyFactory {
    class Args(
        val target: Any?,
        val args: Array<out Any>
    )

    companion object {
        private val firstMethod: Method

        init {
            firstMethod = Extension::class.java.getDeclaredMethod("first", Function1::class.java)
        }

        private fun splitArgs(args: Array<out Any>): Args {
            if (args.isEmpty()) {
                return Args(null, emptyArray())
            }
            if (args.size == 1) {
                return Args(args[0], emptyArray())
            }
            return Args(args[0], args.copyOfRange(1, args.size))
        }

        fun <E : Extension<E>> proxy(clazz: KClass<E>): E {
            val javaClazz = clazz.java
            val proxy =
                Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
                    override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any {
                        val spi = method.getAnnotation(Spi::class.java)
                        val splittedArgs = splitArgs(args)
                        if (spi == null) {
                            return method.invoke(splittedArgs.target, splittedArgs.args)
                        }

                        return firstMethod.invoke(splittedArgs.target,
                            object : Function1<E, Any> {
                                override fun invoke(p1: E): Any {
                                    return method.invoke(p1, splittedArgs.args)
                                }
                            })
                    };
                })
            return proxy as E
        };
    }
}