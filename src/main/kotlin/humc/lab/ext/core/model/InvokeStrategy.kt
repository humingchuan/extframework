package humc.lab.ext.core.model

/**
 * @author: humingchuan
 * @date: 2023-11-21 22:08
 * @description
 */
enum class InvokeStrategy {
    FIRST,
    FIRST_NOT_NULL,
    INVOKE_ALL,
    COLLECT_ALL,
    CUSTOMIZED
}