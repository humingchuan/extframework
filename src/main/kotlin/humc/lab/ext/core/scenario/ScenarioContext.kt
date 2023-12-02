package humc.lab.ext.core.scenario

/**
 * @author: humingchuan
 * @date: 2023-11-21 23:03
 * @description
 */
class ScenarioContext(
    val scenario: String
) {

    companion object {
        private val scenarioThreadLocal: ThreadLocal<ScenarioContext> = ScenarioContextTL()

        fun get(): ScenarioContext? {
            return scenarioThreadLocal.get()
        }

        fun set(context: ScenarioContext) {
            scenarioThreadLocal.set(context)
        }

        fun clear() {
            scenarioThreadLocal.remove()
        }
    }

    class ScenarioContextTL : ThreadLocal<ScenarioContext>() {

    }
}