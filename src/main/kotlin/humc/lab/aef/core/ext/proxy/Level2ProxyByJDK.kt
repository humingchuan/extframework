package humc.lab.aef.core.ext.proxy

import humc.lab.aef.core.ext.api.Combinable
import humc.lab.aef.core.ext.api.ExtPoint
import humc.lab.aef.core.ext.api.InvokeStrategy
import humc.lab.aef.core.ext.invoker.ExtensionInvoker
import humc.lab.aef.core.ext.invoker.ExtensionObserver
import humc.lab.aef.core.session.BusinessSession
import org.springframework.stereotype.Component
import sun.misc.ProxyGenerator
import java.io.FileOutputStream
import java.io.IOException
import java.lang.RuntimeException
import java.lang.reflect.*
import kotlin.reflect.KClass


/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
@Component
class Level2ProxyByJDK(
    private val extensionInvoker: ExtensionInvoker
) {
    private fun getCodeFromMethod(method: Method): String {
        val annotation = method.getAnnotation(ExtPoint::class.java)
        return annotation!!.code
    }

    fun commonProxy(clazz: KClass<*>, observer: ExtensionObserver): Any {
        val javaClazz = clazz.java
        return Proxy.newProxyInstance(
            javaClazz.classLoader, arrayOf(javaClazz)
        ) { _, method, args ->
            val declaringClass = method.declaringClass
            if (declaringClass == Combinable::class.java) {
                throw RuntimeException("Should not invoke ${method.name}, since this is a proxy")
            }

            val extCode = getCodeFromMethod(method)
            extensionInvoker.common(extCode, args, observer)
        }
    }

    fun proxy(clazz: KClass<*>, strategy: InvokeStrategy): Any {
        val javaClazz = clazz.java
        val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
            override fun invoke(proxy: Any, method: Method, args: Array<Any?>?): Any? {
                val declaringClass = method.declaringClass
                if (declaringClass == Combinable::class.java) {
                    throw RuntimeException("Should not invoke ${method.name}, since this is a proxy")
                }

                val extCode = getCodeFromMethod(method)
                return when (strategy) {
                    InvokeStrategy.FIRST -> extensionInvoker.first(extCode, args)
                    InvokeStrategy.INVOKE_ALL -> extensionInvoker.all(extCode, args)
                    InvokeStrategy.FIRST_NOT_NULL -> extensionInvoker.firstNonNull(extCode, args)
                    InvokeStrategy.COLLECT_ALL -> TODO()
                    InvokeStrategy.CUSTOMIZED -> TODO()
                    InvokeStrategy.UNTIL -> TODO()
                }
            }
        })

        return proxy
    }

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

