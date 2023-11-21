package humc.lab.ext.demo

import humc.lab.ext.core.ExtensionCenter
import humc.lab.ext.core.model.Spi

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
object NameSpiImpl1 : NameSpi {
    init {
        ExtensionCenter.register("enrichName", this)
    }

    @Spi
    override fun enrichName(obj: MyBizObj) {
        obj.name = "Good_${obj.name}"
    }

}