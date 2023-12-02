package humc.lab.ext.core.scenario

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:30
 * @description
 */
class ScenarioTree<T>(
    val system: String,
    val root: Scenario<T>
) {
     fun run() {
        root.run()
    }
}