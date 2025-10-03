// Calculadora.kt
package org.example

class Calculadora(private val registry: OperationRegistry) {

    fun calcular(simbolo: String, operandos: List<Double>): Double {
        val operation = registry.getOperation(simbolo)
            ?: throw IllegalArgumentException("Operación '$simbolo' no registrada")

        return operation.execute(operandos)
    }
}