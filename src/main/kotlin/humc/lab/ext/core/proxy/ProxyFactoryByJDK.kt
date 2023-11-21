//package humc.lab.ext.core.proxy
//
//import humc.lab.ext.core.*
//import humc.lab.ext.core.invoker.InvokerFacade
//import humc.lab.ext.core.model.*
//import sun.misc.ProxyGenerator
//import java.io.FileOutputStream
//import java.io.IOException
//import java.lang.reflect.*
//import kotlin.reflect.KClass
//
//
///**
// * @author: humingchuan
// * @date: 2023-11-19 21:01
// * @description
// */
//class ProxyFactoryByJDK {
//    companion object {
//        val fakeExtension = FakeExtension()
//
//        fun <E : Combinable<E>> proxy(clazz: KClass<E>): E {
//            val javaClazz = clazz.java
//            val proxy = Proxy.newProxyInstance(javaClazz.classLoader, arrayOf(javaClazz), object : InvocationHandler {
//                override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
//                    val declaringClass = method.declaringClass
//                    if (declaringClass == Combinable::class.java) {
//                        return method.invoke(fakeExtension, *args)
//                    }
//
//                    return InvokerFacade.first({ e -> method.invoke(e, *args) }, getCode(clazz)!!)
//                }
//            })
//
//            return proxy as E
//        };
//
//        private fun dumpClass(proxy: Any) {
//            // 获取代理类的字节码
//            // 获取代理类的字节码
//            val proxyClassBytes = ProxyGenerator.generateProxyClass(
//                proxy.javaClass.canonicalName,  // 代理类的类名
//                proxy.javaClass.interfaces // 代理类实现的接口列表
//            )
//
//            // 将代理类的字节码保存到文件中
//
//            // 将代理类的字节码保存到文件中
//            try {
//                FileOutputStream("ProxyClass.class").use { fos -> fos.write(proxyClassBytes) }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    class FakeExtension : ExtensionProxyJava<FakeExtension>() {
//        override fun getCode(): String {
//            return "FakeExtension"
//        }
//    }
//
//
//}