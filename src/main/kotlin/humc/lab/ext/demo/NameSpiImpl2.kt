package humc.lab.ext.demo

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
class NameSpiImpl2 : NameSpi {
    override fun changeName(curName: String): String {
        return "${curName}_Haha"
    }

    override fun getCode(): String {
        return "NameSpiImpl2"
    }
}