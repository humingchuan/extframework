package humc.lab.demo

import humc.lab.ext.core.api.cfg.ExtPoint
import humc.lab.ext.core.api.cfg.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
interface NameSpi : Extension<NameSpi> {
    @ExtPoint("enrichName")
    fun enrichName(obj: MyBizObj)
}