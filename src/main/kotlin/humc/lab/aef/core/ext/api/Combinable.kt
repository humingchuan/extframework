package humc.lab.aef.core.ext.api

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-20 12:58
 * @description
 */
interface Combinable<E : Combinable<E>> {
    /**
     * first返回一个对象，这个对象是一个代理，负责记录行为和参数并
     */
    fun first(): E {
        TODO()
    }

    //    fun <T> firstNonNull(): Combinable<E> {
//        return FirstNonNullInvoker<E, T>().invoke(callable, code)
//    }
//
    fun all(): E {
        TODO()
    }
//
//    fun <T> allUntil(
//        code: String,
//        checker: Function1<T?, Boolean>,
//        callable: E.() -> T
//    ): T? {
//        return AllUntilInvoker<E, T>(checker).invoke(callable, code)
//    }
//
//    fun <T> allResult(
//        code: String,
//        callable: E.() -> T
//    ): List<T> {
//        return AllResultInvoker<E, T>().invoke(callable, code)
//    }
}