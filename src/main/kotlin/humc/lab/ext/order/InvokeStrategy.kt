package humc.lab.ext.order

/**
 * @author: humingchuan
 * @date: 2023-11-18 16:48
 * @description
 */
class InvokeStrategy<T>(
    val shouldStop: StopChecker,
    val retProcess: RetProcess<T>,
) {

}

interface StopChecker {
    fun shouldStop(): Boolean
}

interface RetProcess<T> {
    fun processRet(ret: T)
}