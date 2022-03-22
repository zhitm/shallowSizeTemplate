package spbu.kotlin.shallow.plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

const val DEFAULT_SIZE = 8

class AddShallowSizeMethodTest {

    @ParameterizedTest
    @MethodSource("sourceForTest")
    fun test(realValue: Int, expected: Int) {
        assertEquals(expected, realValue)
    }

    companion object {
        @JvmStatic
        fun sourceForTest() = listOf(
            Arguments.of(BaseClass("Hello").shallowSize(), DEFAULT_SIZE),
            Arguments.of(InternalClass(true).shallowSize(), 1),
            Arguments.of(InheritInterfaces(3).shallowSize(), Int.SIZE_BYTES),
            Arguments.of(InheritClass(3).shallowSize(), Int.SIZE_BYTES),
            Arguments.of(NoBackField('c').shallowSize(), 2),
            Arguments.of(PrivateFields(3).shallowSize(), Long.SIZE_BYTES + Int.SIZE_BYTES),
            Arguments.of(
                MultipleFieldsInConstructor(1, 2, 3, 4).shallowSize(),
                Byte.SIZE_BYTES + Short.SIZE_BYTES + Int.SIZE_BYTES + Long.SIZE_BYTES
            ),
            Arguments.of(
                NullablePrimitives(
                    1f, 1.0, 'c',
                    true
                ).shallowSize(), 4 * DEFAULT_SIZE
            ),
            Arguments.of(JavaCharacter(Character('3')).shallowSize(), DEFAULT_SIZE),
            Arguments.of(NoExplicitType(3).shallowSize(), Int.SIZE_BYTES + Long.SIZE_BYTES),
            Arguments.of(OverrideFieldFromClass(4).shallowSize(), Int.SIZE_BYTES),
            Arguments.of(OverrideFieldFromInterface(4).shallowSize(), Int.SIZE_BYTES)
        )
    }
}