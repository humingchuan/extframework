package com.example.demo.scenario1

import com.example.demo.MyBizObj
import humc.lab.aef.core.scenario.BusinessScenario
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-14 22:31
 * @description
 */
@Component
class BusinessScenario1 : BusinessScenario(
    code = "scenario1",
    name = "scenario1",
    supportedProducts = setOf("product1")
) {
    override fun isActive(obj: Any): Boolean {
        if (obj is MyBizObj) {
            return obj.name.length > 5
        }
        return false
    }
}