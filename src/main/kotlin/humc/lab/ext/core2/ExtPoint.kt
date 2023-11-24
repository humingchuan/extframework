package humc.lab.ext.core2

import humc.lab.ext.demo.MyBizObj

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-20 23:29
 * @description
 */
interface ExtPoint {
    val instCode: String
    val extCode: String
}

interface NameSpi : ExtPoint {
    fun first()
    fun enrichName(obj: MyBizObj)
}

//this is an extension of a code
fun enrichName(obj: MyBizObj) {

}

fun first(ext: NameSpi) {

}