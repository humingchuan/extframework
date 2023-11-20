package humc.lab.ext.demo

import humc.lab.ext.core.ExtensionCenter

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
object NameSpiImpl2 : NameSpi {
    init {
        ExtensionCenter.register(NameSpiImpl1.getCode(), this)
    }

    override fun enrichName(obj: MyBizObj) {
        obj.name = "${obj.name}_haha"
    }
}