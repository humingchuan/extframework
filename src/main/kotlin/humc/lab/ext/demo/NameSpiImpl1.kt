package humc.lab.ext.demo

/**
 * @author: humingchuan
 * @date: 2023-11-19 14:32
 * @description
 */
class NameSpiImpl1 : NameSpi {
    override fun changeName(curName: String): String {
        return "Good_$curName"
    }

    override fun getCode(): String {
        return "NameSpiImpl1"
    }
}