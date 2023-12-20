package humc.lab.aef.core.init

import humc.lab.aef.core.ext.api.Combinable
import humc.lab.aef.core.ext.api.Extension
import humc.lab.aef.core.ext.api.Spi
import humc.lab.aef.core.ext.proxy.Level1ProxyByJDK
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import java.lang.reflect.Field
import kotlin.reflect.KClass

/**
 * @author: humingchuan
 * @date: 2023-12-17 22:20
 * @description
 */
@Component
class SpiInjectPostProcessor(
    private val level1ProxyByJDK: Level1ProxyByJDK
) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        val fields = bean::class.java.declaredFields
        fields.forEach {
            injectSpi(it, bean)
        }
        return super.postProcessAfterInitialization(bean, beanName)
    }

    private fun injectSpi(field: Field, bean: Any) {
        var spi = field.getAnnotation(Spi::class.java) ?: return
        val clazz = field.type
        if (!Extension::class.java.isAssignableFrom(clazz)) {
            return
        }
        field.isAccessible = true

        clazz as Class<Combinable<*>>
        val clazz1: KClass<Combinable<*>> = clazz.kotlin
        val proxy = level1ProxyByJDK.proxy(clazz1)
//        val proxy = level1ProxyByJDK.proxy(NameSpi::class)
        field.set(bean, proxy)
    }
}