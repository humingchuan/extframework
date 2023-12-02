package humc.lab.ext.core.scenario

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:23
 * @description
 */
class Scenario<T>(
    val name: String,
    val code: String,
    val config: ScenarioConfig<T>,
    /**
     * we need to make sure there are no recursive references
     */
    val subScenario: Map<String, Scenario<T>>
) {
    init {
        // TODO:
    }
}