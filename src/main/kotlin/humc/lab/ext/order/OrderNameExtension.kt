package humc.lab.ext.order

import humc.lab.ext.core.Extension

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:50
 * @description
 */
abstract class OrderNameExtension : Extension {
    abstract fun changeName(curName: String): String
}