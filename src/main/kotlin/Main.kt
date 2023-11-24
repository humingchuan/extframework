import humc.lab.ext.core.proxy.ProxyFactoryByJDK
import humc.lab.ext.demo.*

fun main(args: Array<String>) {
    //Main2().test2()
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

    val spi: NameSpi = ProxyFactoryByJDK.proxy(NameSpi::class.java)
    var bizObj = MyBizObj("hi")

    spi.first {
        // TODO: 把这里看做是一个声明，而不是真的调用，这样就能检查是否调用错误了?
        enrichName(bizObj)
    }
    spi.enrichName(bizObj)
    println(bizObj.name)
}