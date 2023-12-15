package humc.lab.aef.core.scenario

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:23
 * @description
 */
abstract class BusinessProduct(
    code: String,
    name: String,
    /**
     * 静态排斥场景，跟业务对象无关，总是要排除的
     */
    val staticExcludeScenarios: Set<String>
) : BusinessFeature(code, name) {

}
