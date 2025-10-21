package org.example


class Division : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        require(operandos[1] != 0.0) { "No se puede dividir por cero" }
        return operandos[0] / operandos[1]
    }
    override val numeroOperandos: Int = 2
}
