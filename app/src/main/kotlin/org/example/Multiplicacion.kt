package org.example

class Multiplicacion : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0] * operandos[1]
    }
    override val numeroOperandos: Int = 2
}
