package humc.lab.aef.core.session

import humc.lab.aef.core.scenario.ScenarioCenter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-13 23:26
 * @description
 */
@Component
class BusinessSessionBuilder(
    val scenarioCenter: ScenarioCenter
) {
    private val log: Logger = LoggerFactory.getLogger(BusinessSessionBuilder::class.java)
    fun build(request: Any): BusinessSession {
        val scenario = scenarioCenter.resolveScenario(request)
            ?: throw IllegalArgumentException("Not supported Scenario")
        val products = scenarioCenter.resolveProducts(request)

        val filteredProducts = products.filter { scenario.supportedProducts.contains(it.code) }

        log.debug("session built by {} with products {}", scenario, filteredProducts)
        return object : BusinessSession(toString(), scenario, filteredProducts) {}
    }
}