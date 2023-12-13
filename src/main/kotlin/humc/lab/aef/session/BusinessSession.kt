package humc.lab.aef.session

import humc.lab.aef.scenario.BusinessScenario
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: humingchuan
 * @date: 2023-12-02 15:42
 * @description
 */
abstract class BusinessSession(
    val id: String,
    val activeScenarios: Set<BusinessScenario>
) {
    abstract fun init()

    fun <T> run(task: Callable<T>): T {
        init()

        try {
            enter(this)
            return task.call()
        } finally {
            exit()
        }
    }

    companion object {
        private val sessionMap: MutableMap<String, BusinessSession> = ConcurrentHashMap<String, BusinessSession>()

        private val scenarioThreadLocal: ThreadLocal<Stack<String>> = ScenarioContextTL()

        fun <S : BusinessSession> get(): S? {
            val s = scenarioThreadLocal.get()
            val sessionId = s?.peek() ?: return null
            return sessionMap[sessionId] as S?
        }

        fun enter(context: BusinessSession) {
            var s = scenarioThreadLocal.get()
            if (s == null) {
                s = Stack()
                scenarioThreadLocal.set(s)
            }
            sessionMap[context.id] = context
            s.push(context.id)
        }

        fun exit() {
            val stack = scenarioThreadLocal.get()
            if (stack == null || stack.isEmpty()) {
                scenarioThreadLocal.remove()
            }
            val sessionId = stack.pop()
            sessionMap.remove(sessionId)

            if (stack.isEmpty()) {
                scenarioThreadLocal.remove()
            }
        }
    }

    class ScenarioContextTL : ThreadLocal<Stack<String>>() {

    }
}