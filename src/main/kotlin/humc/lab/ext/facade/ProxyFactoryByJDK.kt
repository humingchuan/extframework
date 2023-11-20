package humc.lab.ext.facade

import humc.lab.ext.core.*
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
class ProxyFactoryByJDK {

    companion object {
        private val firstMethod: Method

        init {
            firstMethod = Extension::class.java.getDeclaredMethod("first", Function1::class.java)
        }

        fun <E : Extension<E>> proxy(clazz: KClass<E>): E {
            val javaClazz = clazz.java
            val proxy =
                Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
                    override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
                        val spi = method.getAnnotation(Spi::class.java)
                        if (spi == null) {
                            return method.invoke(FakeExtension(), *args)
                        }

                        return firstMethod.invoke(proxy,
                                                  object : Function1<E, Any?> {
                                                      override fun invoke(p1: E): Any? {
                                                          return method.invoke(p1, *args)
                                                      }
                                                  })
                    };
                })
            return proxy as E
        };
    }

    class FakeExtension : ExtensionProxyJava<FakeExtension>() {
        override fun getCode(): String {
            return "NameSpi"
        }
    }

}