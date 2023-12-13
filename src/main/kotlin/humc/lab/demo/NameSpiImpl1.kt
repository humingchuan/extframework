package humc.lab.demo

import humc.lab.ext.core.model.Spi

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
object NameSpiImpl1 : NameSpi {
    init {
        humc.lab.aef.ext.core.ExtensionCenter.register("enrichName", "demo",this)
    }

    @Spi
    override fun enrichName(obj: MyBizObj) {
        obj.name = "Good_${obj.name}"
    }

}