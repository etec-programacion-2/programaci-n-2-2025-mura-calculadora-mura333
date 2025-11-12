package org.example

/**
 * Operación unaria para cambiar el signo del elemento superior de la pila.
 */
class Negacion : Operacion {
    override val numOperandos = 1

    override fun ejecutar(operandos: List<Double>): Double {
        // Multiplica el operando por -1
        return -operandos[0]
    }
}