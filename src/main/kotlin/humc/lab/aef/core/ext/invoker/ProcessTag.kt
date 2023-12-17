package humc.lab.aef.core.ext.invoker

/**
 * @author: humingchuan
 * @date: 2023-11-19 13:49
 * @description
 */
enum class ProcessTag {
    CONTINUE,
    SKIP,
    ABORT;

    fun shouldSkip(): Boolean {
        return this == SKIP
    }

    fun shouldInvoke(): Boolean {
        return this == CONTINUE
    }

    fun shouldStop(): Boolean {
        return this == ABORT
    }

    companion object {
        fun goOn(): ProcessTag {
            return CONTINUE
        }

        fun stop(): ProcessTag {
            return ABORT
        }
    }
}