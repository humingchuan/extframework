package humc.lab.aef.spec

/**
 * @author: humingchuan
 * @date: 2023-12-13 23:43
 * @description
 */
class FeatureSpec {
    lateinit var code: String

    /**
     * 用于扫描拓展点实现和场景对象的包路径
     */
    lateinit var packagePrefix: String
}