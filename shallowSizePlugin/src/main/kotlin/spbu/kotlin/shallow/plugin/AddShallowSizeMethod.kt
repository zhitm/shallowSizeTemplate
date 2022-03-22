
package spbu.kotlin.shallow.plugin

import arrow.meta.CliPlugin
import arrow.meta.Meta
import arrow.meta.invoke
import arrow.meta.quotes.Transform
import arrow.meta.quotes.classDeclaration
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.builders.irBlockBody
import org.jetbrains.kotlin.ir.builders.irInt
import org.jetbrains.kotlin.ir.builders.irReturn
import org.jetbrains.kotlin.ir.types.*
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.ir.util.properties

const val DEFAULT_SIZE = 8

fun IrType.byteSize(): Int =
    when {
        this.isChar() -> Char.SIZE_BYTES
        this.isByte() -> Byte.SIZE_BYTES
        this.isShort() -> Short.SIZE_BYTES
        this.isInt() -> Int.SIZE_BYTES
        this.isLong() -> Long.SIZE_BYTES
        this.isUByte() -> UByte.SIZE_BYTES
        this.isUShort() -> UShort.SIZE_BYTES
        this.isULong() -> ULong.SIZE_BYTES
        this.isFloat() -> Float.SIZE_BYTES
        this.isDouble() -> Double.SIZE_BYTES
        this.isBoolean() -> 1
        this.isUnit() -> 1
        else -> DEFAULT_SIZE
    }

val Meta.GenerateShallowSize: CliPlugin
    get() = "Generate shallowSize method" {
        meta(
            classDeclaration(this, { element.isData() }) { declaration ->
                Transform.replace(
                    replacing = declaration.element,
                    newDeclaration = """
                        $`@annotations` $kind $name $`(typeParameters)` $`(params)` $superTypes {
                        fun shallowSize(): Int = TODO()
                        $body
                        }
                        """.`class`
                )
            },
            irClass { clazz ->
                if (clazz.isData) {
                    val shallowSizeFunc = clazz.functions.filter { it.name.toString() == "shallowSize" }.first()
                    shallowSizeFunc.body = DeclarationIrBuilder(pluginContext, shallowSizeFunc.symbol).irBlockBody {
                        +irReturn(irInt(clazz.properties.sumOf { it.backingField?.type?.byteSize() ?: 0 }))
                    }
                }
                clazz
            }
        )
    }
