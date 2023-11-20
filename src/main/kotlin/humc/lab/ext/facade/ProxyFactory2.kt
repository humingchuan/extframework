package humc.lab.ext.facade

import humc.lab.ext.core.Extension
import java.io.File
import java.net.URLClassLoader
import javax.tools.JavaFileObject
import javax.tools.StandardJavaFileManager
import javax.tools.ToolProvider
import kotlin.reflect.KClass


/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
class ProxyFactory2 {
    companion object {
        fun <E : Extension<E>> proxy(clazz: KClass<E>): E {
            var javaCompiler = ToolProvider.getSystemJavaCompiler()
            val path = "/Users/lyy/Desktop/test/humc/lab/ext/facade/NameSpiProxy3.java"
            val clazzName = "humc.lab.ext.facade.NameSpiProxy3"
            val fileManager: StandardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);

            // 3. 使用Java文件管理器对象打开Java源文件
            val files: Iterable<JavaFileObject> = fileManager.getJavaFileObjects(path);

            // 4.Compiler 编译器编译Java源文件
            val result = javaCompiler.getTask(null, fileManager, null, null, null, files)
                .call();
            println(result)

            // 5. 动态加载MyClass类
            val classLoader = URLClassLoader(arrayOf(File("/Users/lyy/Desktop/test").toURI().toURL()))
            val targetClazz = classLoader.loadClass(clazzName)
            val obj = targetClazz.newInstance();
            return obj as E
        }
    }

}