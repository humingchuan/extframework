package humc.lab.ext.core

import humc.lab.ext.core.model.Extension
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.stream.Collectors


/**
 * @author: humingchuan
 * @date: 2023-11-18 16:54
 * @description
 */
object ExtensionCenter {
    private val instanceMap: MutableMap<String, MutableList<Any>> = mutableMapOf()
    fun <E : Extension<E>> getExtensions(code: String): List<E> {
        val ret = instanceMap.get(code)
        if (ret != null) {
            return ret as List<E>
        }

        return emptyList()
    }

    fun <E : Extension<E>> register(code: String, instance: Extension<E>) {
        val list = instanceMap.computeIfAbsent(code) { ArrayList() }
        list.add(instance)
    }
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