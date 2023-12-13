package humc.lab.demo

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
object NameSpiImpl2 : NameSpi {
    init {
        humc.lab.aef.ext.core.ExtensionCenter.register("enrichName", "demo", this)
    }

    override fun enrichName(obj: MyBizObj) {
        obj.name = "${obj.name}_haha"
    }
}