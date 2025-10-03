// AdditionOperation.kt
package org.example

class AdditionOperation : Operation {
    override fun execute(operandos: List<Double>): Double {
        return operandos.sum()
    }
}
