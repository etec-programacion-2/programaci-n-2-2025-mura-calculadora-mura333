package org.example
import kotlin.math.tan
import kotlin.math.PI
import kotlin.math.cos

class Tangente : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        val radianes = operandos[0] * PI / 180.0
        require(cos(radianes) != 0.0) { "Tangente indefinida para este ángulo" }
        return tan(radianes)
    }
    override val numeroOperandos: Int = 1
}
