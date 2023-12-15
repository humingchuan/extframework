package com.example.demo.product1

import com.example.demo.MyBizObj
import com.example.demo.NameSpi
import humc.lab.aef.core.ext.api.Spi
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
@Component
object NameSpiImpl3 : NameSpi {
    @Spi
    override fun enrichName(obj: MyBizObj) {
        obj.name = "what_the_fuck_${obj.name}"
    }

}