package humc.lab.ext.demo

import humc.lab.ext.core.model.ExtPoint
import humc.lab.ext.core.model.Extension
import humc.lab.ext.core.model.Spi

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
interface NameSpi : Extension<NameSpi> {
    @ExtPoint("enrichName")
    fun enrichName(obj: MyBizObj)
}