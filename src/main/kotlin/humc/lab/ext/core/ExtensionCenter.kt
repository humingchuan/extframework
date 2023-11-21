package humc.lab.ext.core

import humc.lab.ext.core.model.ExtImpl
import humc.lab.ext.core.model.ExtPoint
import humc.lab.ext.core.model.Extension
import java.awt.MediaTracker
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.lang.reflect.Method
import java.util.stream.Collectors


/**
 * @author: humingchuan
 * @date: 2023-11-18 16:54
 * @description
 */
object ExtensionCenter {
    private val instanceMap: MutableMap<String, MutableList<ExtImpl>> = mutableMapOf()

    // TODO:
    fun getExtensions(scenario: String, code: String): List<ExtImpl> {
        return instanceMap.get(code) as List<ExtImpl>
    }

    fun <E : Extension<E>> register(code: String, instance: Extension<E>) {
        val list = instanceMap.computeIfAbsent(code) { ArrayList() }
        val instCode = "${code}@${instance.javaClass.name}"

        val method = getMethodByCode(code, instance) ?: throw IllegalArgumentException("Invalid code $code")
        val function: (Array<Any?>?) -> Any? = { args ->
            if (args == null) {
                method.invoke(instance)
            } else {
                method.invoke(instance, *args)
            }
        }
        list.add(
            ExtImpl(code, instCode, function)
        )

    }

    private fun getMethodByCode(code: String, instance: Extension<*>): Method? {
        var clazzList: List<Class<*>> = listOf(instance.javaClass)
        while (clazzList.isNotEmpty()) {
            for (clazz in clazzList) {
                for (method in clazz.declaredMethods) {
                    val extPoint = method.getAnnotation(ExtPoint::class.java)
                    if (extPoint != null) {
                        if (code == extPoint.code) {
                            return method
                        }
                    }
                }
            }
            clazzList = clazzList.flatMap { clazz ->
                clazz.interfaces.filter { Extension::class.java.isAssignableFrom(it) }
            }
        }
        return null
    }


    class AccessingAllClassesInPackage {
        fun findAllClassesUsingClassLoader(packageName: String): Set<Class<*>?> {
            val stream: InputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replace("[.]".toRegex(), "/"))
            val reader = BufferedReader(InputStreamReader(stream))
            return reader.lines()
                .filter { line: String -> line.endsWith(".class") }
                .map { line: String ->
                    getClass(
                        line,
                        packageName
                    )
                }
                .collect(Collectors.toSet())
        }

        private fun getClass(className: String, packageName: String): Class<*>? {
            try {
                return Class.forName(
                    packageName + "."
                            + className.substring(0, className.lastIndexOf('.'))
                )
            } catch (e: ClassNotFoundException) {
                // handle the exception
            }
            return null
        }
    }
}