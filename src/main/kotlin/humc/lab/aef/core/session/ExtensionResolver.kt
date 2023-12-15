package humc.lab.aef.core.session

import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-13 22:46
 * @description
 */
@Component
class ExtensionResolver {
    fun resolveExtImpl(session: BusinessSession, code: String): List<humc.lab.aef.core.ext.ExtImpl> {
        val businessScenario = session.businessScenario
        val activeProducts = session.activeProducts

        val extListFromScenario = businessScenario.getExtList(code)
        val extListFromProducts = activeProducts.flatMap { it.getExtList(code) }

        return extListFromScenario + extListFromProducts
    }
}