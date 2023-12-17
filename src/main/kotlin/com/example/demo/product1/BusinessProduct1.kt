package com.example.demo.product1

import com.example.demo.MyBizObj
import humc.lab.aef.core.scenario.BusinessProduct
import humc.lab.aef.core.scenario.BusinessScenario
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-14 22:31
 * @description
 */
@Component
class BusinessProduct1 : BusinessProduct(
    code = "product1",
    name = "product1",
    staticExcludeScenarios = setOf()
) {
    override fun isActive(obj: Any): Boolean {
        return true
    }
}