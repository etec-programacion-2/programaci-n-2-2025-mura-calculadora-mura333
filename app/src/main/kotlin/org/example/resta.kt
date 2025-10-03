// SubtractionOperation.kt
package org.example

class SubtractionOperation : Operation {
    override fun execute(operandos: List<Double>): Double {
        if (operandos.isEmpty()) return 0.0
        return operandos.drop(1).fold(operandos.first()) { acc, num -> acc - num }
    }
}