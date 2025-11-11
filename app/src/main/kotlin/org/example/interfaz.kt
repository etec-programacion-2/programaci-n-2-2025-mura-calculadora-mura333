package org.example

interface Operacion {
    fun ejecutar(operandos: List<Double>): Double
    val numOperandos: Int // 1 (unaria) o 2 (binaria)
}