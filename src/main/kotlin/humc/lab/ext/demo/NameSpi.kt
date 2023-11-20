package humc.lab.ext.demo

import humc.lab.ext.core.Extension
import humc.lab.ext.core.Spi

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
interface NameSpi : Extension<NameSpi> {
    @Spi("enrichName")
    fun enrichName(obj: MyBizObj)
    override fun getCode(): String {
        return "NameSpi"
    }
}