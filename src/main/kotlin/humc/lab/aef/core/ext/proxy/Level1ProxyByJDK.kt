package humc.lab.aef.core.ext.proxy

import humc.lab.aef.core.ext.ExtImpl
import humc.lab.aef.core.ext.api.*
import humc.lab.aef.core.ext.invoker.*
import org.springframework.stereotype.Component
import java.lang.reflect.*
import kotlin.reflect.KClass


/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
@Component
class Level1ProxyByJDK(
    private val extensionInvoker: ExtensionInvoker,
    private val level2ProxyByJDK: Level2ProxyByJDK,
) {
    private fun getExtPoint(method: Method): ExtPoint {
        return method.getAnnotation(ExtPoint::class.java)!!
    }

    fun proxy(clazz: KClass<*>): Any {
        val javaClazz = clazz.java
        val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
            override fun invoke(proxy: Any, method: Method, args: Array<Any?>?): Any? {
                val declaringClass = method.declaringClass
                if (declaringClass == Combinable::class.java) {
                    val strategy = InvokeStrategy.values().firstOrNull { it.method.equals(method.name, true) }
                    requireNotNull(strategy) { "Not supported method: ${method.name}" }
                    return when (strategy) {
                        InvokeStrategy.FIRST,
                        InvokeStrategy.FIRST_NOT_NULL,
                        InvokeStrategy.INVOKE_ALL,
                        InvokeStrategy.COLLECT_ALL -> level2ProxyByJDK.proxy(clazz, strategy)

                        InvokeStrategy.CUSTOMIZED -> level2ProxyByJDK.commonProxy(clazz, args!![0] as ExtensionObserver)
                        InvokeStrategy.UNTIL -> invokeUntil(clazz, args)
                    }
                }

                val extPoint = getExtPoint(method)
                val extCode = extPoint.code
                return when (extPoint.strategy) {
                    InvokeStrategy.FIRST -> extensionInvoker.first<Any>(extCode, args)
                    InvokeStrategy.FIRST_NOT_NULL -> extensionInvoker.firstNonNull(extCode, args)
                    InvokeStrategy.INVOKE_ALL -> extensionInvoker.all(extCode, args)
                    InvokeStrategy.COLLECT_ALL,
                    InvokeStrategy.CUSTOMIZED,
                    InvokeStrategy.UNTIL -> TODO()
                }
            }
        })

        return proxy
    }

    private fun invokeUntil(clazz: KClass<*>, args: Array<Any?>?): Any {
        val f = args!![0] as Function1<Any?, Boolean>
        return level2ProxyByJDK.commonProxy(clazz, object : ExtensionObserver {
            override fun before(ext: ExtImpl, args: Array<Any?>?): ProcessTag {
                return ProcessTag.goOn()
            }

            override fun <R> after(ext: ExtImpl, ret: R?): ResultHolder<R?> {
                return if (f.invoke(ret)) {
                    ResultHolder(ret, ProcessTag.goOn())
                } else {
                    ResultHolder(ret, ProcessTag.stop())
                }
            }
        })
    }
    //
    //private fun dumpClass(proxy: Any) {
    //    // 获取代理类的字节码
    //    // 获取代理类的字节码
    //    val proxyClassBytes = ProxyGenerator.generateProxyClass(
    //        proxy.javaClass.canonicalName,  // 代理类的类名
    //        proxy.javaClass.interfaces // 代理类实现的接口列表
    //    )
    //
    //    // 将代理类的字节码保存到文件中
    //
    //    // 将代理类的字节码保存到文件中
    //    try {
    //        FileOutputStream("ProxyClass.class").use { fos -> fos.write(proxyClassBytes) }
    //    } catch (e: IOException) {
    //        e.printStackTrace()
    //    }
    //}
}

