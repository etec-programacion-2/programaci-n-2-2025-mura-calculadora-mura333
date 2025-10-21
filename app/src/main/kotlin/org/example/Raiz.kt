package org.example
import kotlin.math.sqrt

class Raiz : Operacion {
    override fun ejecutar(operandos: List<Double>): Double {
        require(operandos[0] >= 0) { "No se puede calcular raíz de número negativo" }
        return sqrt(operandos[0])
    }
    override val numeroOperandos: Int = 1
}