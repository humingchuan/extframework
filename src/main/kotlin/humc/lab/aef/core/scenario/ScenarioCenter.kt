package humc.lab.aef.core.scenario

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-13 22:54
 * @description
 */
@Component
class ScenarioCenter {
    private val log: Logger = LoggerFactory.getLogger(ScenarioCenter::class.java)
    private val scenarioList: MutableMap<String, BusinessScenario> = mutableMapOf()
    private val productList: MutableMap<String, BusinessProduct> = mutableMapOf()

    fun addScenario(scenario: BusinessScenario) {
        log.debug("register scenario:{}", scenario)
        scenarioList[scenario.code] = scenario
    }

    fun addProduct(product: BusinessProduct) {
        log.debug("register product:{}", product)
        productList[product.code] = product
    }

    fun resolveScenario(request: Any): BusinessScenario? {
        return scenarioList.values.firstOrNull { it.isActive(request) }
    }

    fun resolveProducts(request: Any): List<BusinessProduct> {
        return productList.values.filter { it.isActive(request) }

    }

}