package humc.lab.ext.order

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:50
 * @description
 */
class OrderNamePrefixAppender : OrderNameExtension() {
    override fun getCode(): String {
        return "OrderNameExtension2"
    }

    override fun changeName(curName: String): String {
        return "Good_$curName"
    }
}