package humc.lab.aef.core.session

import humc.lab.aef.core.ext.ExtImpl
import humc.lab.aef.core.ext.ExtensionCenter
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-13 22:46
 * @description
 */
@Component
class ExtensionResolver(
    private val extensionCenter: ExtensionCenter
) {
    fun resolveExtImpl(code: String): List<ExtImpl> {
        val session: BusinessSession = BusinessSession.get()!!
        val businessScenario = session.businessScenario
        val activeProducts = session.activeProducts

        val extListFromScenario = extensionCenter.getExtensions(businessScenario.code, code)
        val extListFromProducts = activeProducts.flatMap { extensionCenter.getExtensions(it.code, code) }

        return extListFromScenario + extListFromProducts
    }
}