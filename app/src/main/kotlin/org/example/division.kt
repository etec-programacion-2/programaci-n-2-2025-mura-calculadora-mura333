// DivisionOperation.kt
package org.example

class DivisionOperation : Operation {
    override fun execute(operandos: List<Double>): Double {
        if (operandos.isEmpty()) return 0.0
        return operandos.drop(1).fold(operandos.first()) { acc, num ->
            if (num == 0.0) throw IllegalArgumentException("No se puede dividir entre cero")
            acc / num
        }
    }
}