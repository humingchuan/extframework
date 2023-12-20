package humc.lab.aef.core.ext.proxy

import com.sun.org.apache.bcel.internal.generic.SWITCH
import humc.lab.aef.core.ext.api.Combinable
import humc.lab.aef.core.ext.api.ExtPoint
import humc.lab.aef.core.ext.api.InvokeStrategy
import humc.lab.aef.core.ext.invoker.ExtensionInvoker
import humc.lab.aef.core.session.BusinessSession
import org.springframework.stereotype.Component
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
@Component
class Level1ProxyByJDK(
    private val extensionInvoker: ExtensionInvoker,
    private val level2ProxyByJDK: Level2ProxyByJDK,
) {
    private fun getCodeFromMethod(method: Method): String {
        val annotation = method.getAnnotation(ExtPoint::class.java)
        return annotation!!.code
    }

//    fun <E : Combinable<E>> proxy(clazz: KClass<E>): E {

    fun proxy(clazz: KClass<*>): Any {
        val javaClazz = clazz.java
        val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
            override fun invoke(proxy: Any, method: Method, args: Array<Any?>?): Any? {
                val declaringClass = method.declaringClass
                if (declaringClass == Combinable::class.java) {
                    val strategy = InvokeStrategy.values().firstOrNull { it.method.equals(method.name, true) }
                    requireNotNull(strategy) { "Not supported method: ${method.name}" }
                    return level2ProxyByJDK.proxy(clazz, strategy)
                }
                // TODO:根据拓展点的规格执行不同的方法
                val extCode = getCodeFromMethod(method)
                return extensionInvoker.first(extCode, args)
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

