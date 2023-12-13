package humc.lab.aef.ext.core.invoker

/**
 * @author: humingchuan
 * @date: 2023-11-19 13:49
 * @description
 */
enum class ProcessTag {
    CONTINUE,
    ABORT;

    fun shouldGoOn(): Boolean {
        return this == CONTINUE
    }

    fun shouldStop(): Boolean {
        return !shouldGoOn()
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