package humc.lab.aef.scenario

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:23
 * @description
 */
open class BusinessScenario(
    val code: String,
    val name: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BusinessScenario

        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}
