package humc.lab.aef.core.scenario

import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-13 22:54
 * @description
 */
@Component
class ScenarioCenter {
    private val scenarioList: MutableMap<String, BusinessScenario> = mutableMapOf()
    private val productList: MutableMap<String, BusinessProduct> = mutableMapOf()

    fun addScenario(scenario: BusinessScenario) {
        scenarioList[scenario.code] = scenario
    }

    fun addProduct(product: BusinessProduct) {
        productList[product.code] = product
    }

    fun resolveScenario(request: Any): BusinessScenario? {
        return scenarioList.values.firstOrNull { it.isActive(request) }
    }

    fun resolveProducts(request: Any): List<BusinessProduct> {
        return productList.values.filter { it.isActive(request) }

    }

}