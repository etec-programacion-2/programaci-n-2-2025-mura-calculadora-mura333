package org.example

class Resta : Operacion {
    override val numOperandos = 2
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0] - operandos[1]
    }
}