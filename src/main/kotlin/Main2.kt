import humc.lab.ext.core.proxy.Level1ProxyByJDK
import humc.lab.ext.demo.*

fun main(args: Array<String>) {
    test3()
}

fun test3() {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    NameSpiImpl1
    NameSpiImpl2

    val spi: NameSpi = Level1ProxyByJDK.proxy(NameSpi::class)
    var bizObj = MyBizObj("hi")

    ScenarioContext.set(ScenarioContext("hi"))
    spi.first().enrichName(bizObj)
    spi.enrichName(bizObj)
    println(bizObj.name)
}
