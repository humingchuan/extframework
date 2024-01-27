package com.example.demo.scenario2

import com.example.demo.MyBizObj
import com.example.demo.NameSpi
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
@Component
object NameSpiImpl2 : NameSpi {
    override fun enrichName(obj: MyBizObj) {
        obj.name = "${obj.name}__NameSpiImpl2"
    }
}