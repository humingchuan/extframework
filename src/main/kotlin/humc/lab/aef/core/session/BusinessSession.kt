package humc.lab.aef.core.session

import humc.lab.aef.core.scenario.BusinessProduct
import humc.lab.aef.core.scenario.BusinessScenario
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
    /**
     * 所属的业务场景
     */
    val businessScenario: BusinessScenario,
    /**
     * 激活的产品
     */
    val activeProducts: List<BusinessProduct>,
) {
    fun <T> invoke(task: Callable<T>): T {
        try {
            enter(this)
            return task.call()
        } finally {
            exit()
        }
    }

    companion object {
        private val sessionMap: MutableMap<String, BusinessSession> = ConcurrentHashMap<String, BusinessSession>()

        /**
         * TODO 允许session嵌套可能会是灾难性的，因为很容易忘记初始化session而错误地使用了别人的session
         */
        private val sessionThreadLocal: ThreadLocal<Stack<String>> = ScenarioContextTL()

        fun getScenario(): BusinessScenario {
            val session: BusinessSession? = get()
            return session!!.businessScenario
        }

        fun <S : BusinessSession> get(): S? {
            val s = sessionThreadLocal.get()
            val sessionId = s?.peek() ?: return null
            return sessionMap[sessionId] as S?
        }

        fun enter(context: BusinessSession) {
            var s = sessionThreadLocal.get()
            if (s == null) {
                s = Stack()
                sessionThreadLocal.set(s)
            }
            if (!s.isEmpty()) {
                throw IllegalStateException("Nested session is not supported now")
            }
            sessionMap[context.id] = context
            s.push(context.id)
        }

        fun exit(): String? {
            val stack = sessionThreadLocal.get()
            if (stack == null || stack.isEmpty()) {
                sessionThreadLocal.remove()
                return null
            }

            val sessionId = stack.pop()
            sessionMap.remove(sessionId)

            if (stack.isEmpty()) {
                sessionThreadLocal.remove()
            }
            return sessionId
        }

        fun exitAll() {
            var session: String? = exit()
            while (session != null) {
                session = exit()
            }
        }
    }

    class ScenarioContextTL : ThreadLocal<Stack<String>>() {

    }
}