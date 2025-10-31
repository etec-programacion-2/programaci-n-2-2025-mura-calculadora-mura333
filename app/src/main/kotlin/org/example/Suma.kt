package org.example

class Suma : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0] + operandos[1]
    }
}