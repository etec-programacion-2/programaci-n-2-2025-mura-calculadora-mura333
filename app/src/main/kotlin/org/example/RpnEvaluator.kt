package org.example

class RpnEvaluator { // Ya no necesita Calculadora en el constructor

    fun evaluate(tokens: List<String>): Double {
        val pila = mutableListOf<Double>()

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                pila.add(token.toDouble())
            } else {
                val operacion = OperationRegistry.obtener(token)
                    ?: throw IllegalArgumentException("Operador '$token' no soportado")

                val numOperandos = operacion.numOperandos

                if (pila.size < numOperandos) {
                    throw IllegalArgumentException("Expresión mal formada: se esperaban $numOperandos operandos para '$token'")
                }

                val operandosParaOperacion = mutableListOf<Double>()
                // Extraer N operandos de la pila en orden [op1, op2, ...]
                for (i in 1..numOperandos) {
                    operandosParaOperacion.add(0, pila.removeLast())
                }

                pila.add(operacion.ejecutar(operandosParaOperacion))
            }
        }

        if (pila.size != 1) throw IllegalArgumentException("Expresión mal formada (pila final ${pila.size})")
        return pila[0]
    }

    fun explicarEvaluacion(tokens: List<String>) {
        println("Pila: []")
        val pila = mutableListOf<Double>()

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                pila.add(token.toDouble())
                println("→ \"$token\": $pila")
            } else {
                val operacion = OperationRegistry.obtener(token)
                    ?: throw IllegalArgumentException("Operador '$token' no soportado")

                val numOperandos = operacion.numOperandos

                if (pila.size < numOperandos) {
                    throw IllegalArgumentException("Expresión mal formada: se esperaban $numOperandos operandos para '$token'")
                }

                val operandosParaOperacion = mutableListOf<Double>()
                for (i in 1..numOperandos) {
                    operandosParaOperacion.add(0, pila.removeLast())
                }

                val resultado = operacion.ejecutar(operandosParaOperacion)

                // Formato de explicación
                val operacionStr = if (numOperandos == 1) {
                    "$token(${operandosParaOperacion[0]})"
                } else { // binario
                    "${operandosParaOperacion[0]} $token ${operandosParaOperacion[1]}"
                }

                println("→ \"$token\": $pila    ($operacionStr = $resultado)")
                pila.add(resultado)
            }
        }
        println("Resultado final: ${pila[0]}")
    }
}