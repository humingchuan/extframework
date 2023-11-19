package humc.lab.ext.facade

import humc.lab.ext.demo.NameSpi
import java.util.concurrent.Callable

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:34
 * @description
 */
class NameSpiProxy : NameSpi {
    override fun changeName(curName: String): String {
        val f = { spi: NameSpi ->
            spi.changeName(curName)
        }

        val ret: String? = InvokerFacade.first(f, NameSpi::class)
        return ret!!
    }

    override fun getCode(): String {
        TODO("Not yet implemented")
    }
}