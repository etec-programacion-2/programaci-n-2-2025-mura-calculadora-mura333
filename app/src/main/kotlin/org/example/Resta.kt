package org.example

class Resta : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        return operandos[0] - operandos[1]
    }
}