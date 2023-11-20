package humc.lab.ext.core.proxy

import humc.lab.ext.core.*
import humc.lab.ext.core.invoker.InvokerFacade
import humc.lab.ext.core.model.Extension
import sun.misc.ProxyGenerator
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.*
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
            val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
                override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
                    val spi = method.getAnnotation(Spi::class.java)
                    val extension = FakeExtension()
                    if (spi == null) {
                        // TODO: 这里加上白名单，避免出现空指针
                        return method.invoke(null, *args)
                    }

                    //return firstMethod.invoke(proxy, object : Function1<E, Any?> {
                    //    override fun invoke(p1: E): Any? {
                    //        return method.invoke(p1, *args)
                    //    }
                    //})
                    return InvokerFacade.first(object : Function1<E, Any?> {
                        override fun invoke(p1: E): Any? {
                            return method.invoke(p1, *args)
                        }
                    }, extension.getCode())

                };
            })

            dumpClass(proxy)

            return proxy as E
        };

        private fun dumpClass(proxy: Any) {
            // 获取代理类的字节码
            // 获取代理类的字节码
            val proxyClassBytes = ProxyGenerator.generateProxyClass(
                proxy.javaClass.canonicalName,  // 代理类的类名
                proxy.javaClass.interfaces // 代理类实现的接口列表
            )

            // 将代理类的字节码保存到文件中

            // 将代理类的字节码保存到文件中
            try {
                FileOutputStream("ProxyClass.class").use { fos -> fos.write(proxyClassBytes) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    class FakeExtension : ExtensionProxyJava<FakeExtension>() {
        override fun getCode(): String {
            return "NameSpi"
        }
    }


}