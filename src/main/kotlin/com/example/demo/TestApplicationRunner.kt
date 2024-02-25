package com.example.demo

import humc.lab.aef.core.ext.api.Spi
import humc.lab.aef.core.ext.proxy.Level1ProxyByJDK
import humc.lab.aef.core.session.BusinessSessionBuilder
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

private var hi = "hi_"

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
        var bizObj = MyBizObj(hi)

        sessionBuilder.build(bizObj)
            .invoke {
//              等价于  spi.first().enrichName(bizObj)
                spi.enrichName(bizObj)
                //just NameSpiImpl2 executed
                println("step1 ${bizObj.name}")

                bizObj.name = hi
                spi.runAll().enrichName(bizObj)
                //NameSpiImpl2 executed
                //NameSpiImpl4 executed
                println("step2 ${bizObj.name}")

                bizObj.name = hi
                //just NameSpiImpl2 executed
                spi.until<MyBizObj> { it != null && it.name.length > 10 }
                    .enrichName(bizObj)
                println("step3 ${bizObj.name}")
            }

        hi = "hello"
        bizObj = MyBizObj(hi)
        sessionBuilder.build(bizObj)
            .invoke {
//              等价于  spi.first().enrichName(bizObj)
                spi.enrichName(bizObj)
                //just NameSpiImpl1 executed
                println("step4 ${bizObj.name}")

                bizObj.name = hi
                spi.runAll().enrichName(bizObj)
                //NameSpiImpl1 executed
                //NameSpiImpl3 executed
                println("step5 ${bizObj.name}")

                bizObj.name = hi
                //just NameSpiImpl1 executed
                spi.until<MyBizObj> { it != null && it.name.length > 10 }
                    .enrichName(bizObj)
                println("step6 ${bizObj.name}")
            }
    }

}
