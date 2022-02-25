package spbu.kotlin.shallow.plugin

import arrow.meta.CliPlugin
import arrow.meta.Meta
import arrow.meta.invoke
import arrow.meta.quotes.classDeclaration
import org.jetbrains.kotlin.ir.types.IrType

const val DEFAULT_SIZE = 8

fun IrType.byteSize(): Int =
    TODO("Calculate types for Char, Byte, Short, Int, Long, UByte, UShort, ULong, Float, Double, Boolean, Unit. For nullable and all other types use DEFAULT_SIZE value")

val Meta.GenerateShallowSize: CliPlugin
    get() = "Generate shallowSize method" {
        meta(
            classDeclaration(this, { TODO("Check if the current declaration is a data class") }) { declaration ->
                TODO("Add new shallowSize function without implementation. Please, use arrow.meta.quotes.Transform.replace")
            },
            irClass { clazz ->
                TODO("Only for data classes calculate the sum of sized its properties and replace the shallowSize function body, please use org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder, org.jetbrains.kotlin.ir.builders.irBlockBody, and org.jetbrains.kotlin.ir.builders.irInt to build new function body")
                clazz
            }
        )
    }