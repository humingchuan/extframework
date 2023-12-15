import humc.lab.aef.core.ext.proxy.Level1ProxyByJDK
import com.example.demo.MyBizObj
import com.example.demo.NameSpi
import com.example.demo.scenario1.NameSpiImpl1
import com.example.demo.scenario2.NameSpiImpl2
import humc.lab.aef.core.session.BusinessSessionBuilder

fun main(args: Array<String>) {
    test3()
}

fun test3() {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    val spi: NameSpi = Level1ProxyByJDK.proxy(NameSpi::class)
    var bizObj = MyBizObj("hi")


    BusinessSessionBuilder
    spi.first().enrichName(bizObj)
    spi.enrichName(bizObj)
    println(bizObj.name)
}
