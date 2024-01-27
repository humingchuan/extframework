package humc.lab.aef.core.ext.api

/**
 * @author: humingchuan
 * @date: 2023-11-21 22:08
 * @description
 */
enum class InvokeStrategy(val method: String) {
    FIRST("first"),
    FIRST_NOT_NULL("firstNonNull"),
    INVOKE_ALL("runAll"),
    COLLECT_ALL("callAll"),
    CUSTOMIZED("customized"),
    UNTIL("until"),

}
