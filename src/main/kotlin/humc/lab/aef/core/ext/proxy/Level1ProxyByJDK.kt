package humc.lab.aef.core.ext.proxy

import humc.lab.aef.core.ext.api.Combinable
import humc.lab.aef.core.ext.api.ExtPoint
import humc.lab.aef.core.ext.invoker.InvokerFacade
import humc.lab.aef.core.session.BusinessSession
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
class Level1ProxyByJDK {
    companion object {
        private fun getCodeFromMethod(method: Method): String {
            val annotation = method.getAnnotation(ExtPoint::class.java)
            return annotation!!.code
        }

        fun <E : Combinable<E>> proxy(clazz: KClass<E>): E {
            val javaClazz = clazz.java
            val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
                override fun invoke(proxy: Any, method: Method, args: Array<Any?>?): Any? {
                    val declaringClass = method.declaringClass
                    if (declaringClass == Combinable::class.java) {
                        // TODO: 根据方法名调用不同的代理对象
                        return Level2ProxyByJDK.proxyFirst(clazz)
                    }

                    // TODO:根据拓展点的规格执行不同的方法
                    val scenario = BusinessSession.getScenario().code
                    val extCode = getCodeFromMethod(method)
                    return InvokerFacade.first(scenario, extCode, args)
                }
            })

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


}