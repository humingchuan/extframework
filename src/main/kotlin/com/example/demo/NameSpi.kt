package com.example.demo

import humc.lab.aef.core.ext.api.ExtPoint
import humc.lab.aef.core.ext.api.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
interface NameSpi : Extension<NameSpi> {
    @ExtPoint("enrichName")
    fun enrichName(obj: MyBizObj)
}