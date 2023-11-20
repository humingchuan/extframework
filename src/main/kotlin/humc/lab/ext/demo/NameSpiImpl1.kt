package humc.lab.ext.demo

import humc.lab.ext.core.ExtensionCenter

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
object NameSpiImpl1 : NameSpi {
    init {
        ExtensionCenter.register(getCode(), this)
    }

    override fun enrichName(obj: MyBizObj) {
        obj.name = "Good_${obj.name}"
    }

}