package org.example
import kotlin.math.cos
import kotlin.math.PI

class Coseno : Operacion {
    override val numOperandos = 1
    override fun ejecutar(operandos: List<Double>): Double {
        val radianes = operandos[0] * PI / 180.0
        return cos(radianes)
    }
}