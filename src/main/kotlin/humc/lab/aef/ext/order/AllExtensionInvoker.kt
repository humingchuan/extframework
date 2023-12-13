//package humc.lab.ext.order
//
//import humc.lab.ext.core.api.cfg.Extension
//import kotlin.reflect.KClass
//
///**
// * @author: humingchuan
// * @date: 2023-11-18 16:36
// * @description
// */
//class AllExtensionInvoker<T>() {
//    private val retList: MutableList<T> = mutableListOf()
//    fun <E : Extension> invoke(callable: Function1<E, T>, clazz: KClass<E>): T? {
//        val shouldStop: StopChecker = object : StopChecker {
//            override fun shouldStop(): Boolean {
//                return false
//            }
//        }
//
//        val retProcess: RetProcess<T> = object : RetProcess<T> {
//            override fun processRet(ret: T) {
//                retList.add(ret)
//            }
//        }
//
//        return ExtensionInvoker<T>(InvokeStrategy(shouldStop, retProcess)).invoke(callable, clazz)
//    }
//}
