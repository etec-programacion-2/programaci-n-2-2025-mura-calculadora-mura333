// MultiplicationOperation.kt
package org.example

class MultiplicationOperation : Operation {
    override fun execute(operandos: List<Double>): Double {
        if (operandos.isEmpty()) return 0.0
        return operandos.reduce { acc, num -> acc * num }
    }
}
