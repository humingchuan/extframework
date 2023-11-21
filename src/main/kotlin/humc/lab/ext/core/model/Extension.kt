package humc.lab.ext.core.model

/**
 * @author: humingchuan
 * @date: 2023-11-17 22:39
 * @description
 */
interface Extension<E : Extension<E>> : Combinable<E> {
    // TODO: 拓展点要不要建模 ？
}