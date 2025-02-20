package humc.lab.ext.core.invoker

import humc.lab.ext.core.model.Combinable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AllUntilInvokerTest {

    class TestCombinable(private val value: Int) : Combinable<TestCombinable> {
        override fun combine(other: TestCombinable): TestCombinable {
            return TestCombinable(this.value + other.value)
        }

        fun getValue(): Int {
            return value
        }
    }

    @Test
    fun `test AllUntilInvoker stops when condition is met`() {
        val checker: (Int?) -> Boolean = { it != null && it > 10 }
        val invoker = AllUntilInvoker<TestCombinable, Int>(checker)

        val callable: (TestCombinable) -> Int = { it.getValue() }

        val result = invoker.invoke(callable, "testCode")

        // Assuming the invoker stops when the condition is met
        assertEquals(11, result)
    }

    @Test
    fun `test AllUntilInvoker continues when condition is not met`() {
        val checker: (Int?) -> Boolean = { it != null && it > 20 }
        val invoker = AllUntilInvoker<TestCombinable, Int>(checker)

        val callable: (TestCombinable) -> Int = { it.getValue() }

        val result = invoker.invoke(callable, "testCode")

        // Assuming the invoker continues and no condition is met
        assertEquals(null, result)
    }
}