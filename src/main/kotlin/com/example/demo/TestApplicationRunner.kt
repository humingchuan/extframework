package com.example.demo

import humc.lab.aef.core.ext.api.Spi
import humc.lab.aef.core.ext.proxy.Level1ProxyByJDK
import humc.lab.aef.core.session.BusinessSessionBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import sun.rmi.rmic.Names

/**
 * @author: humingchuan
 * @date: 2023-12-15 22:48
 * @description
 */
@Component
class TestApplicationRunner(
    private val level1ProxyByJDK: Level1ProxyByJDK,
    private val sessionBuilder: BusinessSessionBuilder
) : ApplicationRunner {
    @Spi
    private lateinit var spi: NameSpi
    override fun run(args: ApplicationArguments?) {
        var bizObj = MyBizObj("hi_mybaby")

        sessionBuilder.build(bizObj)
            .invoke {
//                spi.first().enrichName(bizObj)
                spi.enrichName(bizObj)

                spi.all().enrichName(bizObj)
                println(bizObj.name)
            }
    }
}