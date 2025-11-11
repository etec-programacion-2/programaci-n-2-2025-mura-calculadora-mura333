package org.example

class Multiplicacion : Operacion {
    override val numOperandos = 2
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0] * operandos[1]
    }
}