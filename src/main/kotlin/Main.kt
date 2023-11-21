//import humc.lab.ext.core.proxy.ProxyFactoryByJDK
//import humc.lab.ext.demo.*
//
//fun main(args: Array<String>) {
//    test2()
//}
//
//fun test1() {
//    println("Hello World!")
//
//    // Try adding program arguments via Run/Debug configuration.
//    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//
//    NameSpiImpl1
//    NameSpiImpl2
//
//    val spi: NameSpi = NameSpiProxy()
//    var bizObj = MyBizObj("hi")
//
//    var changedName = spi.first { enrichName(bizObj) }
//    println(bizObj.name)
//
//    bizObj = MyBizObj("hi")
//    changedName = spi.all { enrichName(bizObj) }
//    println(bizObj.name)
//
//    bizObj = MyBizObj("hi")
//    changedName = spi.allUntil({ bizObj.name.length > 10 }) { enrichName(bizObj) }
//    println(bizObj.name)
//
//    bizObj = MyBizObj("hi")
//    changedName = spi.allUntil({ bizObj.name.length > 5 }) { enrichName(bizObj) }
//    println(bizObj.name)
//
//    bizObj = MyBizObj("hi")
//    spi.enrichName(bizObj)
//    println(bizObj.name)
//}
//
//fun test2() {
//    println("Hello World!")
//
//    // Try adding program arguments via Run/Debug configuration.
//    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//
//    NameSpiImpl1
//    NameSpiImpl2
//
//    val spi: NameSpi = ProxyFactoryByJDK.proxy(NameSpi::class)
//    var bizObj = MyBizObj("hi")
//
//    spi.first { enrichName(bizObj) }
//    spi.enrichName(bizObj)
//    println(bizObj.name)
//}