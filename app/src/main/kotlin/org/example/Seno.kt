package org.example
import kotlin.math.sin
import kotlin.math.PI

class Seno : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        val radianes = operandos[0] * PI / 180.0
        return sin(radianes)
    }
    override val numeroOperandos: Int = 1
}