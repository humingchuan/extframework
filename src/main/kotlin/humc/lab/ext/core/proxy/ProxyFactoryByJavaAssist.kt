package humc.lab.ext.core.proxy

import humc.lab.ext.core.model.Extension
import javassist.ClassPool
import javassist.CtMethod
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-11-19 21:01
 * @description
 */
class ProxyFactoryByJavaAssist {
    companion object {
        fun <E : Extension<E>> proxy(clazz: KClass<E>): E {
            val pool = ClassPool.getDefault()
            val ctClazz = pool.makeClass("humc.lab.ext.facade.NameSpiProxy3")

            val makeInterface = pool.makeInterface(clazz.java.canonicalName)
            pool.importPackage("humc.lab.ext.demo.MyBizObj")

            ctClazz.addInterface(makeInterface)

            val methodCode = """
    public void enrichName(MyBizObj obj) {
        first(new Function1<NameSpi, Void>() {
            @Override
            public Void invoke(NameSpi nameSpi) {
                nameSpi.enrichName(obj);
                return null;
            }
        });
    }
            """.trimIndent()
            val method = CtMethod.make(methodCode, ctClazz)
            ctClazz.addMethod(method)
            val proxyClazz = ctClazz.toClass()
            return proxyClazz.newInstance() as E
        }
    }
}