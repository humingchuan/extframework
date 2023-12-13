package humc.lab.aef.ext.core.model

/**
 * @author: humingchuan
 * @date: 2023-11-21 22:50
 * @description
 */
class ExtImpl(
    val code: String,
    val instCode: String,
    val method: Function1<Array<Any?>?, Any?>,
    val scenario: String
)
