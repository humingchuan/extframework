//package humc.lab.ext.core.invoker
//
//import humc.lab.ext.core.api.cfg.Combinable
//import humc.lab.ext.core.api.cfg.Extension
//
///**
// * @author: humingchuan
// * @date: 2023-11-19 14:01
// * @description
// */
//class FirstNonNullInvoker<E : Combinable<E>, R> {
//    private val invoker: ObservableExtensionInvoker<E, R>
//
//    init {
//        val stopAfterFirstNonNull = object : ExtensionObserver<E, R> {
//            override fun before(ext: E): ProcessTag {
//                return ProcessTag.goOn()
//            }
//
//            override fun after(ext: E, ret: R?): ResultHolder<R?> {
//                return if (ret != null) {
//                    ResultHolder(ret, ProcessTag.stop())
//                } else {
//                    ResultHolder(null, ProcessTag.goOn())
//                }
//            }
//        }
//        invoker = ObservableExtensionInvoker(listOf(stopAfterFirstNonNull))
//    }
//
//    fun invoke(callable: Function1<E, R>, code: String): R? {
//        return invoker.invoke(callable, code)
//    }
//}