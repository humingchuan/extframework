package humc.lab.aef.core.scenario

import humc.lab.aef.core.ext.ExtImpl

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:23
 * @description
 */
abstract class BusinessFeature(
    val code: String,
    val name: String,
) {

    abstract fun isActive(obj: Any): Boolean
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BusinessFeature

        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }

    override fun toString(): String {
        return "BusinessFeature(code='$code', name='$name')"
    }
}
