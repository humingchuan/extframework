package humc.lab.ext.demo

import humc.lab.ext.core.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:31
 * @description
 */
interface NameSpi : Extension {
    fun changeName(curName: String): String

}