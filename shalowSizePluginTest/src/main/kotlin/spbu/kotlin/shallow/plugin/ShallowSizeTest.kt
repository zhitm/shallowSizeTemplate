package spbu.kotlin.shallow.plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

const val DEFAULT_SIZE = 8

// TODO: rewrite to parameterized tests
class AddShallowSizeMethodTest {
    @Test
    fun baseShallowTest() {
        val x = BaseClass("Hello")
        assertEquals(DEFAULT_SIZE, x.shallowSize())
    }

    @Test
    fun internalModifierTest() {
        val x = InternalClass(true)
        assertEquals(1, x.shallowSize())
    }

    @Test
    fun inheritInterfacesTest() {
        val x = InheritInterfaces(3)
        assertEquals(Int.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun inheritClassTest() {
        val x = InheritClass(3)
        assertEquals(Int.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun noBackFieldTest() {
        val x = NoBackField('c')
        assertEquals(2, x.shallowSize())
    }

    @Test
    fun privateFieldsTest() {
        val x = PrivateFields(3)
        assertEquals(Long.SIZE_BYTES + Int.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun multipleFieldsInConstructorTest() {
        val x = MultipleFieldsInConstructor(1, 2, 3, 4)
        assertEquals(Byte.SIZE_BYTES + Short.SIZE_BYTES + Int.SIZE_BYTES + Long.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun nullablePrimitivesTest() {
        val x = NullablePrimitives(1f, 1.0, 'c', true)
        assertEquals(4 * DEFAULT_SIZE, x.shallowSize())
    }

    @Test
    fun javaCharacterTest() {
        val x = JavaCharacter(Character('3'))
        assertEquals(DEFAULT_SIZE, x.shallowSize())
    }

    @Test
    fun noExplicitTypeTest() {
        val x = NoExplicitType(3)
        assertEquals(Int.SIZE_BYTES + Long.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun overrideFieldFromClassTest() {
        val x = OverrideFieldFromClass(4)
        assertEquals(Int.SIZE_BYTES, x.shallowSize())
    }

    @Test
    fun overrideFieldFromInterfaceTest() {
        val x = OverrideFieldFromInterface(4)
        assertEquals(Int.SIZE_BYTES, x.shallowSize())
    }
}