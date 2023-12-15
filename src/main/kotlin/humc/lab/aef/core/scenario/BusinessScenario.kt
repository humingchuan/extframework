package humc.lab.aef.core.scenario

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:23
 * @description
 */
abstract class BusinessScenario(
    code: String,
    name: String,
    val supportedProducts: Set<String>
) : BusinessFeature(code, name) {
}
