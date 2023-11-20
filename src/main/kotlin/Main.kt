import humc.lab.ext.demo.MyBizObj
import humc.lab.ext.demo.NameSpi
import humc.lab.ext.demo.NameSpiImpl1
import humc.lab.ext.demo.NameSpiImpl2
import humc.lab.ext.facade.NameSpiProxy
import humc.lab.ext.facade.ProxyFactory
import humc.lab.ext.facade.ProxyFactory2
import humc.lab.ext.facade.ProxyFactory3

fun main(args: Array<String>) {
    test2()
}

fun test1() {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    NameSpiImpl1
    NameSpiImpl2

    val spi: NameSpi = NameSpiProxy()
    var bizObj = MyBizObj("hi")

    var changedName = spi.first { enrichName(bizObj) }
    println(bizObj.name)

    bizObj = MyBizObj("hi")
    changedName = spi.all { enrichName(bizObj) }
    println(bizObj.name)

    bizObj = MyBizObj("hi")
    changedName = spi.allUntil({ bizObj.name.length > 10 }) { enrichName(bizObj) }
    println(bizObj.name)

    bizObj = MyBizObj("hi")
    changedName = spi.allUntil({ bizObj.name.length > 5 }) { enrichName(bizObj) }
    println(bizObj.name)

    bizObj = MyBizObj("hi")
    spi.enrichName(bizObj)
    println(bizObj.name)
}

fun test2() {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    NameSpiImpl1
    NameSpiImpl2

    val spi: NameSpi = ProxyFactory2.proxy(NameSpi::class)
    var bizObj = MyBizObj("hi")

    var changedName = spi.enrichName(bizObj)
    println(bizObj.name)
}