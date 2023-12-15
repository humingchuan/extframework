package humc.lab.aef.core.ext

import java.lang.reflect.Method

/**
 * @author: humingchuan
 * @date: 2023-11-21 22:50
 * @description
 */
class ExtImpl(
    val code: String,
    val instCode: String,
    val method: Method,
    val scenario: String
)
