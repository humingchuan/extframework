package humc.lab.ext.demo

import humc.lab.ext.core.model.Extension
import humc.lab.ext.core.model.Spi

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
@Spi("NameSpi")
interface NameSpi : Extension<NameSpi> {
    @Spi("enrichName")
    fun enrichName(obj: MyBizObj)
    override fun getCode(): String {
        return "NameSpi"
    }
}