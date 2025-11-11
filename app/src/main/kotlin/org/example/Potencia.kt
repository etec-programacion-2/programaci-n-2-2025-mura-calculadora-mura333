package org.example
import kotlin.math.pow

class Potencia : Operacion {
    override val numOperandos = 2
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0].pow(operandos[1])
    }
}