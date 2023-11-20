//package humc.lab.ext.order
//
//import humc.lab.ext.core.model.Extension
//import humc.lab.ext.core.ExtensionCenter
//import kotlin.reflect.KClass
//
///**
// * @author: humingchuan
// * @date: 2023-11-18 16:36
// * @description
// */
//class ExtensionInvoker<T>(
//    private val strategy: InvokeStrategy<T>
//) {
//    fun <E : Extension> invoke(callable: Function1<E, T>, clazz: KClass<E>): T? {
//        val extensions = ExtensionCenter.getExtensions(clazz)
//        var ret: T? = null
//        for (extension in extensions) {
//            ret = callable.invoke(extension)
//            if (strategy.shouldStop.shouldStop()) {
//                return ret
//            }
//            strategy.retProcess.processRet(ret)
//        }
//        return ret
//    }
//}
